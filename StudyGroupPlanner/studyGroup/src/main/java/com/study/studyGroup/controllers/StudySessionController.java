package com.study.studyGroup.controllers;

import static com.study.studyGroup.util.JwtUtil.parseUserId;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.studyGroup.domain.CreateStudySessionRequest;
import com.study.studyGroup.domain.DeleteStudySessionRequest;
import com.study.studyGroup.domain.UpdateStudySessionRequest;
import com.study.studyGroup.domain.dtos.CreateStudySessionRequestDto;
import com.study.studyGroup.domain.dtos.CreateStudySessionResponseDto;
import com.study.studyGroup.domain.dtos.DeleteStudySessionRequestDto;
import com.study.studyGroup.domain.dtos.ListStudySessionResponseDto;
import com.study.studyGroup.domain.dtos.RegisterForStudySessionRequestDto;
import com.study.studyGroup.domain.dtos.UpdateStudySessionRequestDto;
import com.study.studyGroup.domain.dtos.UpdateStudySessionResponseDto;
import com.study.studyGroup.domain.entities.StudySession;
import com.study.studyGroup.mappers.StudySessionMapper;
import com.study.studyGroup.services.StudySessionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin 
@RestController
@RequestMapping(path = "/api/v1/studySessions")
public class StudySessionController {
    private final StudySessionMapper studySessionMapper;
    private final StudySessionService studySessionService;

    public StudySessionController(StudySessionMapper studySessionMapper, StudySessionService studySessionService) {
        this.studySessionMapper = studySessionMapper;
        this.studySessionService = studySessionService;
    }

    @PostMapping(path = "/createSession")
    public ResponseEntity<CreateStudySessionResponseDto> createStudySession(
        @Valid @RequestBody CreateStudySessionRequestDto createStudySessionRequestDto, @AuthenticationPrincipal Jwt jwt) {

        UUID userId = parseUserId(jwt);
        System.out.println(createStudySessionRequestDto.getDescription());

        CreateStudySessionRequest createStudySessionRequest = studySessionMapper.fromDto(createStudySessionRequestDto);

        System.out.println(createStudySessionRequest.getDescription());

        StudySession studySession = studySessionService.createStudySession(createStudySessionRequest, userId);

        CreateStudySessionResponseDto createdStudySessionDto = studySessionMapper.toDto(studySession);

        System.out.println("------------------------->>>>" + createdStudySessionDto.getDescription());

        return new ResponseEntity<>(createdStudySessionDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateSession")
    public ResponseEntity<UpdateStudySessionResponseDto> updateStudySession(
        @Valid @RequestBody UpdateStudySessionRequestDto updateStudySessionRequestDto, @AuthenticationPrincipal Jwt jwt) {

        UpdateStudySessionRequest updateStudySessionRequest = studySessionMapper.fromUpdateStudySessionRequestDto(updateStudySessionRequestDto);

        UUID userId = parseUserId(jwt);
        UUID sessionId = updateStudySessionRequest.getId();

        // StudySession updatedSession = studySessionService.updateStudySession(sessionId, updateStudySessionRequest);
        StudySession updatedSession = studySessionService.updateStudySessionForUser(sessionId, userId, updateStudySessionRequest);

        UpdateStudySessionResponseDto updateStudySessionResponseDto = studySessionMapper.toUpdateStudySesisonResponseDto(updatedSession);

        return ResponseEntity.ok(updateStudySessionResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ListStudySessionResponseDto>> listStudySessions(Pageable pageable) {
        Page<StudySession> sessions = studySessionService.listStudySessions(pageable);

        return ResponseEntity.ok(sessions.map(studySessionMapper::toListStudySessionResponseDto));
    }

    @GetMapping(path = "/forUser") 
    public ResponseEntity<Page<ListStudySessionResponseDto>> listStudySessionsForUser(@AuthenticationPrincipal Jwt jwt, Pageable pageable) {
        UUID userId = parseUserId(jwt);

        Page<StudySession> sessions = studySessionService.listStudySessionsForUser(pageable, userId);

        return ResponseEntity.ok(sessions.map(studySessionMapper::toListStudySessionResponseDto));
    }

    @DeleteMapping(path = "/deleteSession")
    public ResponseEntity<Void> deleteEvent(@AuthenticationPrincipal Jwt jwt,  @Valid @RequestBody DeleteStudySessionRequestDto deleteStudySessionRequestDto) {
        DeleteStudySessionRequest request = studySessionMapper.fromDeleteStudySessionRequestDto(deleteStudySessionRequestDto);

        UUID sessionId = request.getSessionId();
        UUID userId = parseUserId(jwt);

        studySessionService.deleteStudySessionForUser(userId, sessionId);

        // studySessionService.deleteStudySession(sessionId);

        return ResponseEntity.noContent().build();
    }

    // Currently returns nothing but a 200 response - Maybe change in future?
    @PostMapping("/register")
    public ResponseEntity<Void> registerForSession(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody RegisterForStudySessionRequestDto registerForStudySessionRequestDto) {
        UUID userId = parseUserId(jwt);
        UUID sessionId = registerForStudySessionRequestDto.getSessionId();

        studySessionService.registerForSession(sessionId, userId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/register")
    public ResponseEntity<Page<ListStudySessionResponseDto>> getRegisteredSessions(@AuthenticationPrincipal Jwt jwt, Pageable pageable) {
        UUID userId = parseUserId(jwt);

        Page<StudySession> sessions = studySessionService.getRegisteredStudySessionsForUser(userId, pageable);

        return ResponseEntity.ok(sessions.map(studySessionMapper::toListStudySessionResponseDto));  
    }

    @DeleteMapping("/register") 
    public ResponseEntity<Void> unregisterForSession(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody RegisterForStudySessionRequestDto registerForStudySessionRequestDto) {
        UUID userId = parseUserId(jwt);
        UUID sessionId = registerForStudySessionRequestDto.getSessionId();

        studySessionService.unregisterForSession(sessionId, userId);

        return ResponseEntity.noContent().build();
    }
}
