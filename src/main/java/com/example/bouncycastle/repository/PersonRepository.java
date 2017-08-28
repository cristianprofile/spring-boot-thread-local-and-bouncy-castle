package com.example.bouncycastle.repository;

import com.example.bouncycastle.Model.Person;
import com.example.bouncycastle.threadlocal.MyThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class PersonRepository {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Person> getAll() {

        String transactionId = MyThreadLocal.getTransactionId();
        this.logger.debug("3. Init operation repository layer Transaction id:{}",transactionId);
        Person person = new Person();
        person.setName("Pepe");
        person.setSurname("Palmer");
        person.setAge(12);
        person.setAdress("Prado Street");
        List<Person> people = Arrays.asList(person);
        this.logger.debug("4. End operation repository layer Transaction id:{}",transactionId);
        return people;


    }
}
