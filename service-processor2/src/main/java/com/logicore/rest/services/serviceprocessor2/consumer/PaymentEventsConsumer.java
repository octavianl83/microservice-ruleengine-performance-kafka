package com.logicore.rest.services.serviceprocessor2.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicore.rest.services.serviceprocessor2.KafkaService;
import com.logicore.rest.services.serviceprocessor2.RuleEngineProxy;
import com.logicore.rest.services.serviceprocessor2.ServiceProcessed2;
import com.logicore.rest.services.serviceprocessor2.model.Product;
import com.logicore.rest.services.serviceprocessor2.payment.PaymentMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentEventsConsumer {

    @Autowired
    RuleEngineProxy ruleEngineProxy;

    @Autowired
    KafkaService kafkaService;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "${topics.listen}")
    @SendTo()
    public String onMessage(ConsumerRecord<String, String> customerRecord) throws Exception {
        log.info("Receive ConsumerRecord : {}", customerRecord);

        PaymentMessage paymentMessage = objectMapper.readValue(customerRecord.value(), PaymentMessage.class);
        Product product = ruleEngineProxy.getDiscount("gold", 32);

        String reply = (String) kafkaService.kafkaRequestReply(paymentMessage);
        log.info("Processing response service2 {}", reply);

        return reply;
    }

}
