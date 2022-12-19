package com.logicore.rest.services.serviceprocessor4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicore.rest.services.serviceprocessor4.payment.PaymentMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
@Service
@Slf4j
public class KafkaService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topics.reply}")
    private String REPLY_TOPIC;
    @Autowired
    ObjectMapper objectMapper;

}