package com.github.prgrms.social.repository.post;

import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.post.Post;
import com.github.prgrms.social.model.user.User;

public interface PostLikeRepository {

  void like(Id<User, Long> userId, Id<Post, Long> postId);

}