package com.example.batchxml.repository;

import com.example.batchxml.model.LegalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LERecordRepository extends JpaRepository<LegalRecord,Long> {
}
