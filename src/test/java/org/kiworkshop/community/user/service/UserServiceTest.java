package org.kiworkshop.community.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kiworkshop.community.user.domain.User;
import org.kiworkshop.community.user.dto.LoginUserParams;
import org.kiworkshop.community.user.dto.request.SaveUserParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Autowired
  HttpSession session;

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
  public void login_fail() {
    /* Given */
    String name = "myang";
    String password = "mm";
    String email = "myang@myang.com";
    SaveUserParams saveUserParams = new SaveUserParams(name, password, email);
    Long userId = userService.saveUser(saveUserParams);

    LoginUserParams loginUserParams = new LoginUserParams(email, "unknownPAssword");
    /* Then: 로그인에 실패하면 Runtime exception throw */
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
      /* when: userService.login을 호출하여 로그인 시도 */
      userService.login(loginUserParams, session);
    });

  }

}