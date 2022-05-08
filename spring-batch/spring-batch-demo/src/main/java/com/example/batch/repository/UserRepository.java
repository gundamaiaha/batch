package com.example.batch.repository;

import com.example.batch.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Page<User> findByStatusAndEmailVerified(String status, boolean emailVerified, Pageable pageable);
}
