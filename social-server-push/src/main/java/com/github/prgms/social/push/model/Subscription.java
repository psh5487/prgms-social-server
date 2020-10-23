package com.github.prgms.social.push.model;

import com.github.prgrms.social.message.PushSubscribedMessage;
import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.user.User;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class Subscription {

  private Long seq;

  private String notificationEndPoint;

  private String publicKey;

  private String auth;

  private Id<User, Long> userId;

  private LocalDateTime createAt;

  protected Subscription() {
  }

  public Subscription(Long seq, String notificationEndPoint, String publicKey, String auth, Id<User, Long> userId) {
    this.seq = seq;
    this.notificationEndPoint = notificationEndPoint;
    this.publicKey = publicKey;
    this.auth = auth;
    this.userId = userId;
    this.createAt = defaultIfNull(createAt, now());
  }


  public static Subscription of(PushSubscribedMessage pushSubscribedMessage) {
    return new Subscription(pushSubscribedMessage.getSeq(), pushSubscribedMessage.getNotificationEndPoint(), pushSubscribedMessage.getPublicKey(), pushSubscribedMessage.getAuth(),
      Id.of(User.class, pushSubscribedMessage.getUserId()));
  }

  public Long getSeq() {
    return seq;
  }

  public Id<User, Long> getUserId() {
    return userId;
  }

  public String getNotificationEndPoint() {
    return notificationEndPoint;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public String getAuth() {
    return auth;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public PushSubscribedMessage toMessage() {
    PushSubscribedMessage.PushSubscribedMessageBuilder builder = new PushSubscribedMessage.PushSubscribedMessageBuilder();
    builder.seq(this.seq);
    builder.notificationEndPoint(this.notificationEndPoint);
    builder.publicKey(this.publicKey);
    builder.auth(this.auth);
    builder.userId(this.userId.value());
    builder.createAt(this.createAt);
    return builder.build();
  }

  public static final class SubscriptionBuilder {
    private Long seq;
    private String notificationEndPoint;
    private String publicKey;
    private String auth;
    private Id<User, Long> userId;
    private LocalDateTime createAt;

    public SubscriptionBuilder() {
    }

    public SubscriptionBuilder(Subscription subscription) {
      this.seq = subscription.seq;
      this.notificationEndPoint = subscription.notificationEndPoint;
      this.publicKey = subscription.publicKey;
      this.auth = subscription.auth;
      this.userId = subscription.userId;
      this.createAt = subscription.createAt;
    }

    public SubscriptionBuilder seq(Long seq) {
      this.seq = seq;
      return this;
    }

    public SubscriptionBuilder notificationEndPoint(String notificationEndPoint) {
      this.notificationEndPoint = notificationEndPoint;
      return this;
    }

    public SubscriptionBuilder publicKey(String publicKey) {
      this.publicKey = publicKey;
      return this;
    }

    public SubscriptionBuilder auth(String auth) {
      this.auth = auth;
      return this;
    }

    public SubscriptionBuilder userId(Id<User, Long> userId) {
      this.userId = userId;
      return this;
    }

    public SubscriptionBuilder createAt(LocalDateTime createAt) {
      this.createAt = createAt;
      return this;
    }

    public Subscription build() {
      Subscription subscription = new Subscription(seq, notificationEndPoint, publicKey, auth, userId);
      subscription.createAt = this.createAt;
      return subscription;
    }
  }


  @Override
  public String toString() {
    return "Subscription{" +
      "seq=" + seq +
      ", notificationEndPoint='" + notificationEndPoint + '\'' +
      ", publicKey='" + publicKey + '\'' +
      ", auth='" + auth + '\'' +
      ", userId=" + userId +
      ", createAt=" + createAt +
      '}';
  }

}
