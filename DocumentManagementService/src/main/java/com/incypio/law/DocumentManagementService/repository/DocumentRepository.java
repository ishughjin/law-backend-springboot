package com.incypio.law.DocumentManagementService.repository;

import com.incypio.law.DocumentManagementService.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
    Page<Document> findAllByUserId(Long userId, Pageable pageable);
}
