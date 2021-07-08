package com.example.viewproducerapp.service.impl;

import com.example.viewproducerapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author created by cengizhan on 2.07.2021
 */
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;

    @Override
    public void produceEvent() {
        try {
            File file = ResourceUtils.getFile("classpath:product-views.json");
            FileReader fileStream = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileStream);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Thread.sleep(250);
                kafkaTemplate.send(topic, line);
                System.out.println("gönderildi");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
