package com.spring.restful.webservices.demos.restfulwebservices.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendNewUser(Integer id, String name, Date birthDate)
    {
        kafkaTemplate.send("users", id + ":" + name + ":" + birthDate);

    }
}
