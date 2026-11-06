package com.study.studyGroup.services.impl;

import java.util.Optional;
import java.util.UUID;

import static com.study.studyGroup.util.JwtUtil.parseUserId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.study.studyGroup.domain.CreateStudySessionRequest;
import com.study.studyGroup.domain.UpdateStudySessionRequest;
import com.study.studyGroup.domain.entities.StudySession;
import com.study.studyGroup.domain.entities.StudySessionStatusEnum;
import com.study.studyGroup.domain.entities.User;
import com.study.studyGroup.repositories.StudySessionRepository;
import com.study.studyGroup.repositories.UserRepository;
import com.study.studyGroup.services.StudySessionService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

// TODO: Update session status according to current time

@Service
public class StudySessionServiceImpl implements StudySessionService {
    private final StudySessionRepository studySessionRepository;
    private final UserRepository userRepository;

    public StudySessionServiceImpl(StudySessionRepository studySessionRepository, UserRepository userRepository) {
        this.studySessionRepository = studySessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional 
    public StudySession createStudySession(CreateStudySessionRequest request, UUID id) {
        User organizer = userRepository.findById(id).orElseThrow();

        StudySession session = new StudySession();

        session.setName(request.getName());
        session.setDescription(request.getDescription());
        session.setSessionStart(request.getSessionStart());
        session.setSessionEnd(request.getSessionEnd());
        session.setLocation(request.getLocation());
        session.setStatus(StudySessionStatusEnum.UPCOMING);

        // Sets the organizing user, and adds them to the attending list
        session.setOrganizer(organizer);
        session.getAttendees().add(organizer);

        return studySessionRepository.save(session);
    }

    @Override 
    @Transactional 
    public StudySession updateStudySessionForUser(UUID sessionId, UUID userId, UpdateStudySessionRequest request) {
        if (null == request.getId()) {
            throw new Error("Study session id cannot be null");
        }

        if (!sessionId.equals(request.getId())) {
            throw new Error("Cannot update the ID of a study session");
        }

        // StudySession studySession = studySessionRepository.findById(sessionId).get();
        StudySession studySession = studySessionRepository.findByIdAndOrganizerId(sessionId, userId).orElseThrow();

        if (studySession == null) {
            throw new Error("Study session with ID " + sessionId.toString() + " does not exist");
        }

        studySession.setName(request.getName());
        studySession.setDescription(request.getDescription());
        studySession.setSessionStart(request.getSessionStart());
        studySession.setSessionEnd(request.getSessionEnd());
        studySession.setLocation(request.getLocation());
        studySession.setStatus(request.getStatus());

        return studySessionRepository.save(studySession);
    }

    @Override 
    @Transactional 
    public void deleteStudySession(UUID sessionId) {
        studySessionRepository.findById(sessionId).ifPresent(studySessionRepository::delete);
    }

    @Override 
    @Transactional 
    public void deleteStudySessionForUser(UUID userId, UUID sessionId) {
        getStudySessionForUser(userId, sessionId).ifPresent(studySessionRepository::delete);
    }

    @Override 
    public Optional<StudySession> getStudySessionForUser(UUID userId, UUID sessionId) {
        return studySessionRepository.findByIdAndOrganizerId(sessionId, userId);
    }

    @Override
    public Page<StudySession> listStudySessions(Pageable pageable) {
        return studySessionRepository.findAll(pageable);
    }

    @Override 
    public Page<StudySession> listStudySessionsForUser(Pageable pageable, UUID userId) {
        return studySessionRepository.findByOrganizerId(userId, pageable);
    }

    @Override 
    public void registerForSession(UUID sessionId, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();

        StudySession session = studySessionRepository.findById(sessionId).orElseThrow();

        session.getAttendees().add(user);
        user.getAttendedSessions().add(session);

        studySessionRepository.save(session);
        userRepository.save(user);
    }

    @Override 
    @Transactional 
    public void unregisterForSession(UUID sessionId, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();

        StudySession session = studySessionRepository.findById(sessionId).orElseThrow();

        session.getAttendees().remove(user);
        user.getAttendedSessions().remove(session);

        studySessionRepository.save(session);
        userRepository.save(user);
    }

    @Override 
    public Page<StudySession> getRegisteredStudySessionsForUser(UUID userId, Pageable pageable) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
        
        return studySessionRepository.findByAttendees_Id(userId, pageable);
    }
}
