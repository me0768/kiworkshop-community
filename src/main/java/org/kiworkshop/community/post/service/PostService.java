package org.kiworkshop.community.post.service;

import lombok.RequiredArgsConstructor;
import org.kiworkshop.community.user.domain.Post;
import org.kiworkshop.community.user.domain.User;
import org.kiworkshop.community.post.dto.SavePostParams;
import org.kiworkshop.community.post.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;

  public Long savePost(SavePostParams savePostParams, User user) {
    Post post = postRepository.save(new Post(savePostParams.getTitle(), savePostParams.getContent(), user));
    return post.getId();
  }
}
