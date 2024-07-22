package com.alperkyoruk.hms.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alperkyoruk.hms.entities.User;


public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findById(int id);

    User findByEmail(String email);

}
