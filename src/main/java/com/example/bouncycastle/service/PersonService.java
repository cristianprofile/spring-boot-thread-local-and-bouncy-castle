package com.example.bouncycastle.service;

import com.example.bouncycastle.Model.Person;
import com.example.bouncycastle.repository.PersonRepository;
import com.example.bouncycastle.threadlocal.MyThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonRepository personRepository;

   public List<Person> getAllPersons(){
       String transactionId = MyThreadLocal.getTransactionId();
       this.logger.debug("2. Init operation service layer Transaction id:{}",transactionId);
       List<Person> people = personRepository.getAll();
       this.logger.debug("5. End operation service layer Transaction id:{}",transactionId);
       return people;

   }

}
