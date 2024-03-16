package com.example.demo.repository;

import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByPhoneNumberAndPazzW0rd(String phoneNumber, String pazzW0rd);

    User findByPhoneNumber(String phoneNumber);

    User findByUserId(int userId);

}
