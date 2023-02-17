package com.javamaster.bank_card_demo_application_v2.repository;

import com.javamaster.bank_card_demo_application_v2.entity.User;
import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.*;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer userId);

    List<User> findAllByStatus (CardType status, Pageable pageable);

}
