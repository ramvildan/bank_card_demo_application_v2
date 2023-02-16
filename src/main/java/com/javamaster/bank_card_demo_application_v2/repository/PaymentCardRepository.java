package com.javamaster.bank_card_demo_application_v2.repository;

import com.javamaster.bank_card_demo_application_v2.entity.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Integer> {
}
