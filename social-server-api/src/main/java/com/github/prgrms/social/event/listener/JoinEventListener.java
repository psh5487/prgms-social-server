package com.github.prgrms.social.event.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.prgrms.social.event.JoinEvent;
import com.github.prgrms.social.message.UserJoinedMessage;
import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.user.User;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class JoinEventListener implements AutoCloseable {

  @Value("${spring.kafka.topic.user-joined}")
  private String userJoinedTopic;

  private final Logger log = LoggerFactory.getLogger(JoinEventListener.class);

  private final EventBus eventBus;

  private final KafkaTemplate<String, UserJoinedMessage> kafkaTemplate;

  public JoinEventListener(EventBus eventBus, KafkaTemplate<String, UserJoinedMessage> kafkaTemplate) {
    this.eventBus = eventBus;
    this.kafkaTemplate = kafkaTemplate;
    eventBus.register(this);
  }

  @Subscribe
  public void handleJoinEvent(JoinEvent event) throws JsonProcessingException {
    String name = event.getName();
    Id<User, Long> userId = event.getUserId();
    log.info("user {}, userId {} joined!", name, userId);

    this.kafkaTemplate.send(userJoinedTopic, new UserJoinedMessage(event));
  }

  @KafkaListener(id = "api", topics = "test")
  public void helloEventListen(String helloEvent) {
    log.info("Got Message from Kafka {}", helloEvent);
  }

  @Override
  public void close() throws Exception {
    eventBus.unregister(this);
  }

}
