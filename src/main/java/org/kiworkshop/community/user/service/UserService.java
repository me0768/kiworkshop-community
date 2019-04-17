package org.kiworkshop.community.user.service;

import lombok.RequiredArgsConstructor;
import org.kiworkshop.community.user.domain.User;
import org.kiworkshop.community.user.dto.request.SaveUserParams;
import org.kiworkshop.community.user.dto.response.UserDetailResponse;
import org.kiworkshop.community.user.dto.response.UserListResponse;
import org.kiworkshop.community.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public Long saveUser(SaveUserParams params) {
    User user = userRepository.save(new User(params));
    return user.getId();
  }

  public UserListResponse getUsers() {
    return new UserListResponse(userRepository.findAll().stream()
            .map(user -> new UserDetailResponse(user.getName(), user.getEmail()))
            .collect(Collectors.toList()));
  }

  public User findUserById(Long userId) {
    return userRepository.findById(userId).orElseThrow(RuntimeException::new);
  }

}
