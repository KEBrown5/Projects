package com.study.studyGroup.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.study.studyGroup.domain.CreateStudySessionRequest;
import com.study.studyGroup.domain.UpdateStudySessionRequest;
import com.study.studyGroup.domain.entities.StudySession;

public interface StudySessionService {
    StudySession createStudySession(CreateStudySessionRequest request, UUID id);

    Page<StudySession> listStudySessions(Pageable pageable);

    StudySession updateStudySessionForUser(UUID userId, UUID sessionId, UpdateStudySessionRequest request);

    void deleteStudySession(UUID sessionId);

    void registerForSession(UUID sessionId, UUID userId);

    void unregisterForSession(UUID sessionId, UUID userId);

    Page<StudySession> listStudySessionsForUser(Pageable pageable, UUID userId);

    void deleteStudySessionForUser(UUID userId, UUID sessionId);

    Optional<StudySession> getStudySessionForUser(UUID userId, UUID sessionId);

    Page<StudySession> getRegisteredStudySessionsForUser(UUID userId, Pageable pageable);
}
