package com.example.batchxml.repository;

import com.example.batchxml.model.LegalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LERepository extends JpaRepository<LegalEntity,Long> {
}
