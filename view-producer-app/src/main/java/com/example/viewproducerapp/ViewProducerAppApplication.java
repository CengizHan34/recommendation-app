package com.example.viewproducerapp;

import com.example.viewproducerapp.service.EventService;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.Properties;

/**
 * @author created by cengizhan on 3.07.2021
 */
@SpringBootApplication
public class ViewProducerAppApplication implements CommandLineRunner {
    @Autowired
    private EventService eventService;

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.address}")
    private String kafkaAddress;

    public static void main(String[] args) {
        SpringApplication.run(ViewProducerAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        AdminClient admin = AdminClient.create(config);
        //creating new topic
        System.out.println("-- creating --");
        NewTopic newTopic = new NewTopic(topic, 1, (short) 1);
        admin.createTopics(Collections.singleton(newTopic));
        eventService.produceEvent();
    }

//    @Override
//    public void run(String... args) throws Exception {
//        EventService eventService = new EventServiceImpl();
//        eventService.produceEvent();
//    }
}