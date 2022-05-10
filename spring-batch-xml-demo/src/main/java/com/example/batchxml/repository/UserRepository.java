package com.example.batchxml.repository;

import com.example.batchxml.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
