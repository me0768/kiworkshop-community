package org.kiworkshop.community.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kiworkshop.community.user.domain.User;
import org.kiworkshop.community.user.dto.request.SaveUserParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void 비밀번호가_암호화된_새로운_유저_생성하기() {
    String name = "myang";
    String password = "mm";
    String email = "myang@myang.com";
    SaveUserParams saveUserParams = new SaveUserParams(name, password, email);
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    Long userId = userService.saveUser(saveUserParams);
    User resultUser = userService.findUserById(userId);

    assertThat(bCryptPasswordEncoder.matches(password, resultUser.getPassword())).isTrue();
  }
  @Test
  public void 비밀번호를_BCryptEncoder를_이용해서_암호화하기() {
    SaveUserParams saveUserParams = new SaveUserParams("myang", "비밀번호", "myang@myang.com");
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  }

}