package com.github.prgrms.social.message;

import com.github.prgrms.social.controller.user.UserDto;
import com.github.prgrms.social.event.CommentCreatedEvent;
import com.github.prgrms.social.model.post.Writer;

public class CommentCreatedMessage {

  private UserDto postWriter;

  private Long postId;

  private Long userId;

  private Writer commentWriter;

  private Long commentId;

  public CommentCreatedMessage() {
  }

  public CommentCreatedMessage(UserDto postWriter, CommentCreatedEvent event) {
    this.postWriter = postWriter;
    this.postId = event.getPostId().value();
    this.userId = event.getUserId().value();
    this.commentWriter = event.getCommentWriter();
    this.commentId = event.getCommentId().value();
  }

  @Override
  public String toString() {
    return "CommentCreatedMessage{" +
      "postWriter=" + postWriter +
      ", postId=" + postId +
      ", userId=" + userId +
      ", commentWriter=" + commentWriter +
      ", commentId=" + commentId +
      '}';
  }

  public UserDto getPostWriter() {
    return postWriter;
  }

  public Long getPostId() {
    return postId;
  }

  public Long getUserId() {
    return userId;
  }

  public Writer getCommentWriter() {
    return commentWriter;
  }

  public Long getCommentId() {
    return commentId;
  }

}
