package com.ww.es.service.impl;

import com.ww.es.pojo.Account;
import com.ww.es.config.AccountRepository;
import com.ww.es.config.ElasticsearchRestClientConfig;
import com.ww.es.service.AccountService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.util.CloseableIterator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ElasticsearchRestClientConfig restClient;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Optional<Account> findById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public void save(Account account) {
        Account save = accountRepository.save(account);

    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public Optional<Account> findOne(long id) {
        return accountRepository.findById((int) id);
    }

    @Override
    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }


    @Override
    public List<Account> findByName(String fieldName, String keyword) {
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder(fieldName, keyword);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(termQueryBuilder).build();
        return (List<Account>) elasticsearchTemplate.queryForList(searchQuery, Account.class);
    }

    @Override
    public Page<Account> findByNameWithPage(String fieldName) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withFields(fieldName)
                .withPageable(PageRequest.of(20000, 10))
                .withSort(new FieldSortBuilder(fieldName).order(SortOrder.DESC))
                .build();
        return elasticsearchTemplate.queryForPage(searchQuery, Account.class);
    }

    @Override
    public List<Account> findByNameDeepth(String fieldName) {
        // 浅分页
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withFields(fieldName)
                .withPageable(PageRequest.of(0, 10))
                .build();

        long scollTimeInMillis = 1000;
        Page<Account> scroll = elasticsearchTemplate.startScroll(scollTimeInMillis, searchQuery, Account.class);

        String scrollId = ((ScrolledPage) scroll).getScrollId();
        List<Account> accounts = new ArrayList<>();
        // scroll里面的内容就是类似Iterator（hasNext）
        while (scroll.hasContent()) {
            System.out.println(scroll.getContent());
            accounts.addAll(scroll.getContent());
            scrollId = ((ScrolledPage) scroll).getScrollId();
            scroll = elasticsearchTemplate.continueScroll(scrollId, scollTimeInMillis, Account.class);
        }
        elasticsearchTemplate.clearScroll(scrollId);
        return accounts;
    }

    public List<Account> findByStream() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withFields("firstname")
                .withPageable(PageRequest.of(0, 10))
                .build();

        CloseableIterator<Account> stream = elasticsearchTemplate.stream(searchQuery, Account.class);
        List<Account> accounts = new ArrayList<>();
        while (stream.hasNext()) {
            accounts.add(stream.next());
        }
        return accounts;
    }

    @Override
    public void findNameByRest() {
        SearchRequest searchRequest = new SearchRequest("bank");
        SearchSourceBuilder searchBuilder = new SearchSourceBuilder();
        searchBuilder.query(QueryBuilders.matchAllQuery());
        searchBuilder.from(0);
        searchBuilder.size(10);
        try {
            SearchResponse response = restClient.restHighLevelClient().search(searchRequest);
            SearchHit[] searchHits = response.getHits().getHits();
            for (SearchHit hit : searchHits) {
                System.out.println(hit.getSourceAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<Account> findByName(String name, PageRequest pageRequest) {
        return accountRepository.findByName(name, pageRequest);
    }

    @Override
    public Page<Account> findByAge(int age, PageRequest pageRequest) {
        return accountRepository.findByAge(age, pageRequest);
    }

}
