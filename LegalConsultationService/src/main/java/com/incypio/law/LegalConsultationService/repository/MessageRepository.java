package com.incypio.law.LegalConsultationService.repository;

import com.incypio.law.LegalConsultationService.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findAllByConversationId(UUID conversation_id);
    Optional<Page<Message> > findByConversationId(UUID conversationId, Pageable pageable);

}
