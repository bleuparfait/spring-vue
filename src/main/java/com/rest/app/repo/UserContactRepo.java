package com.rest.app.repo;

import com.rest.app.domain.User;
import com.rest.app.domain.UserContact;
import com.rest.app.domain.UserContactId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserContactRepo extends JpaRepository<UserContact, UserContactId> {
    List<UserContact> findBySender(User user);

    List<UserContact> findByReceiver(User user);

    UserContact findBySenderAndReceiver(User sender, User receiver);

}
