package com.example.streamreaderapp.service;

import com.example.streamreaderapp.entity.ProductView;
import com.example.streamreaderapp.repository.ProductViewRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author created by cengizhan on 3.07.2021
 */
@Service
@RequiredArgsConstructor
public class KafkaListenerServiceImpl implements KafkaListenerService {
    private final ProductViewRepository productViewRepository;
    private final ObjectMapper mapper;

    @Override
    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group.id}")
    public void listen(@Payload String data) {
        try {
            ProductView productView = mapper.readValue(data, ProductView.class);
            productViewRepository.save(productView);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
