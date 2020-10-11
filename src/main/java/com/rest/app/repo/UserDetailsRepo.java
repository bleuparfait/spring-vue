package com.rest.app.repo;

import com.rest.app.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<User, String> {
    @EntityGraph(attributePaths = { "subscriptions", "subscribers", "contacts" })
    Optional<User> findById(String s);

}
