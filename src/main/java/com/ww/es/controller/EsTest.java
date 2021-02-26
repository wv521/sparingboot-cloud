package com.ww.es.controller;

import com.ww.es.pojo.User;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

public class EsTest {

    @Autowired
     private ElasticsearchTemplate elasticsearchTemplate;

    public void test(){
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("content","中国");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(termQueryBuilder)
                .build();
        List<User> users = elasticsearchTemplate.queryForList(searchQuery, User.class);

    }


}
