package org.kiworkshop.community.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kiworkshop.community.user.dto.request.SaveUserParams;

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

  public boolean matchPassword(String password) {
    return this.password.equals(password);
  }

}
