package com.example.bouncycastle.controller;

import com.example.bouncycastle.Model.Person;
import com.example.bouncycastle.service.PersonService;
import com.example.bouncycastle.threadlocal.MyThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonService personService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Person> helloWorld() {
        String transactionId = MyThreadLocal.getTransactionId();
        this.logger.debug("1. Init operation controller Transaction id:{}",transactionId);
        List<Person> allPersons = personService.getAllPersons();
        this.logger.debug("6. End operation controller Transaction id:{}",transactionId);
        return allPersons;
    }
}
