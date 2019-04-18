package org.kiworkshop.community.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.kiworkshop.community.user.domain.User;
import org.kiworkshop.community.user.dto.LoginUserParams;
import org.kiworkshop.community.user.dto.request.SaveUserParams;
import org.kiworkshop.community.user.dto.response.UserListResponse;
import org.kiworkshop.community.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserRestController {
  private final UserService userService;

  public UserRestController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public UserListResponse getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/me")
  public User getLoginUser(HttpSession session) {
    return (User) session.getAttribute("LOGIN_USER");
  }

  @PostMapping("/login")
  public void Login(@RequestBody LoginUserParams userParams, HttpSession session) {
    String auth = (String) session.getAttribute("LOGIN_USER");
    log.info("login: userParams={}", userParams, auth);
    userService.login(userParams, session);
  }

  @PostMapping
  public void saveUser(@RequestBody SaveUserParams userParams) {
    log.info("save user request userParams={}", userParams);
    userService.saveUser(userParams);
  }


}
