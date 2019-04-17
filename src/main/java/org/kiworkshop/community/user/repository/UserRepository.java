package org.kiworkshop.community.user.repository;

import org.kiworkshop.community.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
