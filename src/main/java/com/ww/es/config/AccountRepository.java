package com.ww.es.config;

import com.ww.es.pojo.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

//泛型的参数分别是实体类型和主键类型
public interface AccountRepository extends ElasticsearchRepository<Account, Long> {

     Optional<Account> findById(int id);

     void delete(Account account);

    List<Account> findAll();

     Page<Account> findByName(String name, PageRequest pageRequest);

     Page<Account> findByAge(int age, PageRequest pageRequest);
}
