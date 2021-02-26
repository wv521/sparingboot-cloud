package com.ww.es.controller;

import com.ww.es.pojo.Account;
import com.ww.es.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/{id}")
    @ResponseBody
    public Account getAccountById(@PathVariable int id) {
        Optional<Account> opt = accountService.findById(id);
        Account account = opt.get();
        return account;
    }

    @RequestMapping("/{field}/{name}")
    @ResponseBody
    public List<Account> getAccountByName(@PathVariable String field, @PathVariable String name) {
        List<Account> list = accountService.findByName(field, name);
        return list;
    }

    @RequestMapping("/page/{field}")
    @ResponseBody
    public Page<Account> getByNameWithPage(@PathVariable String field) {
        Page<Account> page = accountService.findByNameWithPage(field);
        return page;
    }

    @RequestMapping("/deepth/{field}")
    @ResponseBody
    public List<Account> getByNameDeepth(@PathVariable String field) {
        List<Account> list = accountService.findByNameDeepth(field);
        return list;
    }

    @RequestMapping("/rest")
    @ResponseBody
    public void getByRestClient() {
        accountService.findNameByRest();
    }

    @RequestMapping("/save")
    @ResponseBody
    public void Save() {
//        Account account = new Account(2001, 2001, "Micheal", "Jackson", 35, "M",
//                new BigDecimal("999999"), "test@126.com", "CA", "Belvoir",
//                "499 Laurel Avenue", "");
        Account account = new Account();
        accountService.save(account);
    }

}
