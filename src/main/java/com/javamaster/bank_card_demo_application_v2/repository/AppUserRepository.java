package com.javamaster.bank_card_demo_application_v2.repository;

import com.javamaster.bank_card_demo_application_v2.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByNameAndSurname(String name, String surname);
}
