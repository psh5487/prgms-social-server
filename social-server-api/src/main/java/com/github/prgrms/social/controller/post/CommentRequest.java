package com.github.prgrms.social.controller.post;

import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.post.Comment;
import com.github.prgrms.social.model.post.Post;
import com.github.prgrms.social.model.post.Writer;
import com.github.prgrms.social.model.user.User;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CommentRequest {

  @ApiModelProperty(value = "내용", required = true)
  private String contents;

  protected CommentRequest() {}

  public String getContents() {
    return contents;
  }

  public Comment newComment(Id<User, Long> userId, Id<Post, Long> postId, Writer writer) {
    return new Comment(userId, postId, writer, contents);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("contents", contents)
      .toString();
  }

}