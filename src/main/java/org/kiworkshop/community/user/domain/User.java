package org.kiworkshop.community.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kiworkshop.community.user.dto.request.SaveUserParams;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long id;

  private String name;
  private String password;
  private String email;

//  @OneToMany
//  @JoinColumn(name = "USER_ID")
//  private List<Post> posts;

  public User(SaveUserParams params) {
    this.name = params.getName();
    this.password = params.getPassword();
    this.email = params.getEmail();
  }

  public User(String name, String password, String email) {
    this.name = name;
    this.password = password;
    this.email = email;
  }

  public boolean matchPassword(String password, PasswordEncoder passwordEncoder) {
    return passwordEncoder.matches(password, this.password);
  }

}
