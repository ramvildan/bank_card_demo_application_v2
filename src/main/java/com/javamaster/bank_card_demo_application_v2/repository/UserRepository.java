package com.javamaster.bank_card_demo_application_v2.repository;

import com.javamaster.bank_card_demo_application_v2.entity.User;
import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer userId);

    Page<User> findAllByStatusAndCurrencyType(CardType status, CurrencyType currencyType, Pageable pageable);
}
