package org.kiworkshop.community.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kiworkshop.community.user.domain.User;
import org.kiworkshop.community.user.dto.LoginUserParams;
import org.kiworkshop.community.user.dto.request.SaveUserParams;
import org.kiworkshop.community.user.dto.response.UserDetailResponse;
import org.kiworkshop.community.user.dto.response.UserListResponse;
import org.kiworkshop.community.user.exception.PasswordMismatchException;
import org.kiworkshop.community.user.exception.UserNotFoundException;
import org.kiworkshop.community.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder bCryptPasswordEncoder;

  public Long saveUser(SaveUserParams params) {
    String encodedPassword = bCryptPasswordEncoder.encode(params.getPassword());
    User user = new User(params.getName(), encodedPassword, params.getEmail());
    return userRepository.save(user).getId();
  }

  public UserListResponse getUsers() {
    return new UserListResponse(userRepository.findAll().stream()
            .map(user -> new UserDetailResponse(user.getName(), user.getEmail()))
            .collect(Collectors.toList()));
  }

  public User findUserById(Long userId) {
    return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
  }

  public void login(LoginUserParams userParams, HttpSession session) {
    String email = userParams.getEmail();
    // FIXME: 2019-04-19 throw custom exception
    User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    if (user.matchPassword(userParams.getPassword(), bCryptPasswordEncoder)) {
      log.info("로그인 성공! userParams={}", userParams);
      session.setAttribute("LOGIN_USER", user);
      return;
    }
    throw new PasswordMismatchException();
  }
}
