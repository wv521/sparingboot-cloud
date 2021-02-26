package com.ww.es.service;

import com.ww.es.pojo.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> findById(int id);

    List<Account> findByName(String field, String name);

    Page<Account> findByNameWithPage(String field);

    List<Account> findByNameDeepth(String field);

    void findNameByRest();

    void save(Account account);

    Page<Account> findByName(String name, PageRequest pageRequest);

    Page<Account> findByAge(int age, PageRequest pageRequest);

    void delete(Account account);

    Optional<Account> findOne(long id);

    List<Account> findAll();
}
