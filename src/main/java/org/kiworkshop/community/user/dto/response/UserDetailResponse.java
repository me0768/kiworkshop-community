package org.kiworkshop.community.user.dto.response;

import lombok.Getter;

@Getter
public class UserDetailResponse {
  private String name;
  private String email;

  public UserDetailResponse(String name, String email) {
    this.name = name;
    this.email = email;
  }
}
