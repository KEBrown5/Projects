package com.study.studyGroup.mappers.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.study.studyGroup.domain.CreateStudySessionRequest;
import com.study.studyGroup.domain.DeleteStudySessionRequest;
import com.study.studyGroup.domain.UpdateStudySessionRequest;
import com.study.studyGroup.domain.dtos.CreateStudySessionRequestDto;
import com.study.studyGroup.domain.dtos.CreateStudySessionResponseDto;
import com.study.studyGroup.domain.dtos.DeleteStudySessionRequestDto;
import com.study.studyGroup.domain.dtos.ListStudySessionResponseDto;
import com.study.studyGroup.domain.dtos.UpdateStudySessionRequestDto;
import com.study.studyGroup.domain.dtos.UpdateStudySessionResponseDto;
import com.study.studyGroup.domain.dtos.UserSummaryDto;
import com.study.studyGroup.domain.entities.StudySession;
import com.study.studyGroup.domain.entities.User;
import com.study.studyGroup.mappers.StudySessionMapper;

@Component
public class StudySessionMapperImpl implements StudySessionMapper {

    @Override
    public CreateStudySessionRequest fromDto(CreateStudySessionRequestDto dto) {
        // System.out.println("--------------------------------------->" + dto.getDescription());


        return new CreateStudySessionRequest(
            dto.getName(),
            dto.getDescription(),
            dto.getSessionStart(),
            dto.getSessionEnd(),
            dto.getLocation()
            // dto.getStatus()
        );
    }

    @Override
    public CreateStudySessionResponseDto toDto(StudySession studySession) {
        System.out.println("--------------------------------------->" + studySession.getDescription());

        return new CreateStudySessionResponseDto(
            studySession.getId(),
            studySession.getName(),
            studySession.getDescription(),
            studySession.getSessionStart(),
            studySession.getSessionEnd(),
            studySession.getLocation(),
            studySession.getStatus(),
            studySession.getCreatedAt(),
            studySession.getUpdatedAt()
        );
    }

    @Override 
    public ListStudySessionResponseDto toListStudySessionResponseDto(StudySession studySession) {
        ListStudySessionResponseDto responseDto = new ListStudySessionResponseDto();

        responseDto.setSessionId(studySession.getId());
        responseDto.setName(studySession.getName());
        responseDto.setDescription(studySession.getDescription());
        responseDto.setSessionStart(studySession.getSessionStart());
        responseDto.setSessionEnd(studySession.getSessionEnd());
        responseDto.setLocation(studySession.getLocation());
        responseDto.setStatus(studySession.getStatus());

        User organizer = studySession.getOrganizer();
        responseDto.setOrganizer(new UserSummaryDto(organizer.getName(), organizer.getEmail(), organizer.getId()));

        // List<User> attendees = studySession.getAttendees();
        List<UserSummaryDto> attendees = studySession.getAttendees().stream().map(user -> new UserSummaryDto(user.getName(), user.getEmail(), user.getId())).collect(Collectors.toList());
        responseDto.setAttendees(attendees);
        
        return responseDto;
    }

    @Override 
    public UpdateStudySessionRequest fromUpdateStudySessionRequestDto(UpdateStudySessionRequestDto dto) {
        return new UpdateStudySessionRequest(
            dto.getSessionId(),
            dto.getName(),
            dto.getDescription(),
            dto.getSessionStart(),
            dto.getSessionEnd(),
            dto.getLocation(),
            dto.getStatus()
        );
    }

    @Override public DeleteStudySessionRequest fromDeleteStudySessionRequestDto(DeleteStudySessionRequestDto dto) {
        return new DeleteStudySessionRequest(
            dto.getId()
        );
    }

    @Override 
    public UpdateStudySessionResponseDto toUpdateStudySesisonResponseDto(StudySession studySession) {
        return new UpdateStudySessionResponseDto(
            studySession.getId(),
            studySession.getName(),
            studySession.getDescription(),
            studySession.getSessionStart(),
            studySession.getSessionEnd(),
            studySession.getLocation(),
            studySession.getStatus(),
            studySession.getCreatedAt(),
            studySession.getUpdatedAt()
        );
    }
}
