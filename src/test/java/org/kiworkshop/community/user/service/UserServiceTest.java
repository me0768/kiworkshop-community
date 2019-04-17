package org.kiworkshop.community.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kiworkshop.community.user.dto.request.SaveUserParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void 새로운_유저_생성하기() {
    String name = "myang";
    String password = "mm";
    String email = "myang@myang.com";

    SaveUserParams saveUserParams = new SaveUserParams(name, password, email);

    userService.saveUser(saveUserParams);
  }

}