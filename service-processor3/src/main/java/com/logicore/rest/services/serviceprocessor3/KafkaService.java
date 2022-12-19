package com.logicore.rest.services.serviceprocessor3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicore.rest.services.serviceprocessor3.payment.PaymentMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class KafkaService {
    @Autowired
    private ReplyingKafkaTemplate<String, Object, Object> template;

    @Value("${topics.send}")
    private String SEND_TOPICS;

    @Autowired
    ObjectMapper objectMapper;

    public Object kafkaRequestReply(PaymentMessage request) throws Exception {
        String value = objectMapper.writeValueAsString(request);
        ProducerRecord<String, Object> record = new ProducerRecord<>(SEND_TOPICS, String.valueOf(request.getTransactionId()), value);

        log.info("Sending ProducerRecord : {}", record);
        RequestReplyFuture<String, Object, Object> replyFuture = template.sendAndReceive(record);
        SendResult<String, Object> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);
        ConsumerRecord<String, Object> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
        return consumerRecord.value();
    }
}