package org.kiworkshop.community.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kiworkshop.community.post.service.PostService;
import org.kiworkshop.community.user.domain.Post;
import org.kiworkshop.community.user.domain.User;
import org.kiworkshop.community.post.dto.SavePostParams;
import org.kiworkshop.community.user.dto.request.SaveUserParams;
import org.kiworkshop.community.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostServiceTest {
  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  @Autowired
  private PostRepository postRepository;

  @Test
  public void 새로운_글_작성하기() {
    SaveUserParams saveUserParams = new SaveUserParams("myang", "myang", "myang@myaang.com");
    Long userId = userService.saveUser(saveUserParams);
    User user = userService.findUserById(userId);
    SavePostParams savePostParams = new SavePostParams("제목", "내용");

    Long postId = postService.savePost(savePostParams, user);

    Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);

    assertThat(post.getTitle()).isEqualTo(savePostParams.getTitle());
    assertThat(post.getWriter().getId()).isEqualTo(savePostParams.getContent());
    assertThat(post.getWriter().getId()).isEqualTo(userId);
    assertThat(post.getWriter().getEmail()).isEqualTo(saveUserParams.getEmail());

  }
}