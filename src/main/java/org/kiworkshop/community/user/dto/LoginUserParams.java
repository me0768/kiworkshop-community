package org.kiworkshop.community.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginUserParams {
  private String email;
  private String password;
}
