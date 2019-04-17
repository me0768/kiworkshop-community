package org.kiworkshop.community.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.kiworkshop.community.user.dto.request.SaveUserParams;
import org.kiworkshop.community.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserRestController {
  private final UserService userService;

  public UserRestController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public void saveUser(@RequestBody SaveUserParams userParams) {
    log.info("save user request userParams={}", userParams);
    userService.saveUser(userParams);
  }

}
