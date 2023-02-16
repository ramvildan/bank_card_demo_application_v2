package com.javamaster.bank_card_demo_application_v2.repository;

import com.javamaster.bank_card_demo_application_v2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer userId);
}
