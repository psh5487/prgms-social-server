package com.github.prgrms.social.message;

import com.github.prgrms.social.event.JoinEvent;

public class UserJoinedMessage {

  private Long userId;

  private String name;

  public UserJoinedMessage() {
  }

  public UserJoinedMessage(JoinEvent event) {
    this.userId = event.getUserId().value();
    this.name = event.getName();
  }

  public Long getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

}
