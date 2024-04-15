package com.incypio.law.LegalConsultationService.repository;

import com.incypio.law.LegalConsultationService.entity.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findById(UUID id);
    Page<Conversation> findAllByUserId(Long userId, Pageable pageable);

}
