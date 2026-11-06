package com.study.studyGroup.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.studyGroup.domain.entities.StudySession;

@Repository
public interface StudySessionRepository extends JpaRepository<StudySession, UUID> {
    Page<StudySession> findByOrganizerId(UUID id, Pageable pageable);
    Optional<StudySession> findByIdAndOrganizerId(UUID sessionId, UUID userId);
    Page<StudySession> findByAttendees_Id(UUID userId, Pageable pageable);

}
