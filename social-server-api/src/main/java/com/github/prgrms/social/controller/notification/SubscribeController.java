package com.github.prgrms.social.controller.notification;

import com.github.prgrms.social.controller.ApiResult;
import com.github.prgrms.social.message.PushSubscribedMessage;
import com.github.prgrms.social.security.JwtAuthentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

import static com.github.prgrms.social.controller.ApiResult.OK;

@RestController
@RequestMapping("api")
@Api(tags = "Push 구독 APIs")
public class SubscribeController {

  private static final Logger logger = LoggerFactory.getLogger(SubscribeController.class);

  @Value("${spring.kafka.topic.subscription-request}")
  private String requestTopic;

  @Value("${spring.kafka.topic.subscription-reply}")
  private String requestReplyTopic;

  private final ReplyingKafkaTemplate<String, PushSubscribedMessage, PushSubscribedMessage> kafkaTemplate;

  public SubscribeController(ReplyingKafkaTemplate<String, PushSubscribedMessage, PushSubscribedMessage> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostMapping("/subscribe")
  @ApiOperation(value = "Push 구독")
  public ApiResult<PushSubscribedMessage> subscribe(@AuthenticationPrincipal JwtAuthentication authentication,
                                                    @RequestBody PushSubscribedMessage subscription) throws ExecutionException, InterruptedException {
    PushSubscribedMessage.PushSubscribedMessageBuilder subscriptionBuilder = new PushSubscribedMessage.PushSubscribedMessageBuilder(subscription);
    subscriptionBuilder.userId(authentication.id.value());

    ProducerRecord<String, PushSubscribedMessage> record = new ProducerRecord<>(requestTopic, subscriptionBuilder.build());
    record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

    RequestReplyFuture<String, PushSubscribedMessage, PushSubscribedMessage> sendAndReceive = kafkaTemplate.sendAndReceive(record);

    ConsumerRecord<String, PushSubscribedMessage> consumerRecord = sendAndReceive.get();

    logger.info("success to subscribe {}", consumerRecord.value());

    return OK(consumerRecord.value());
  }

}
