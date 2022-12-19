package com.logicore.rest.services.serviceprocessor4.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
@EnableKafka
public class KafkaConfig {
    @Bean
    public NewTopic libraryEvents() {
        return TopicBuilder.name("serviceprocessor3-reply")
                .partitions(2)
                .replicas(1)
                .build();
    }
}