package com.logicore.rest.services.serviceprocessor2.config;

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

    @Value("${topics.reply}")
    private String REPLY_TOPICS;
    @Bean //register and configure replying kafka template
    public ReplyingKafkaTemplate<String, Object, Object> replyingTemplate(
            ProducerFactory<String, Object> pf,
            ConcurrentMessageListenerContainer<String, Object> repliesContainer) {
        return new ReplyingKafkaTemplate<>(pf, repliesContainer);
    }

    @Bean //register ConcurrentMessageListenerContainer bean
    public ConcurrentMessageListenerContainer<String, Object> repliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory) {
        ConcurrentMessageListenerContainer<String, Object> repliesContainer = containerFactory.createContainer(REPLY_TOPICS);
//        repliesContainer.getContainerProperties().setGroupId(CONSUMER_GROUPS);
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }

    @Bean
    public NewTopic libraryEvents() {
        return TopicBuilder.name("serviceprocessor2-reply")
                .partitions(2)
                .replicas(1)
                .build();
    }
}