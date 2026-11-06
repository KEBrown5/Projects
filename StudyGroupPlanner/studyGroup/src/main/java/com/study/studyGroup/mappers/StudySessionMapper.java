package com.study.studyGroup.mappers;

import com.study.studyGroup.domain.CreateStudySessionRequest;
import com.study.studyGroup.domain.DeleteStudySessionRequest;
import com.study.studyGroup.domain.UpdateStudySessionRequest;
import com.study.studyGroup.domain.dtos.CreateStudySessionRequestDto;
import com.study.studyGroup.domain.dtos.CreateStudySessionResponseDto;
import com.study.studyGroup.domain.dtos.DeleteStudySessionRequestDto;
import com.study.studyGroup.domain.dtos.ListStudySessionResponseDto;
import com.study.studyGroup.domain.dtos.UpdateStudySessionRequestDto;
import com.study.studyGroup.domain.dtos.UpdateStudySessionResponseDto;
import com.study.studyGroup.domain.entities.StudySession;

public interface StudySessionMapper {

    CreateStudySessionRequest fromDto(CreateStudySessionRequestDto dto);

    CreateStudySessionResponseDto toDto(StudySession studySession);

    ListStudySessionResponseDto toListStudySessionResponseDto(StudySession studySession);

    UpdateStudySessionResponseDto toUpdateStudySesisonResponseDto(StudySession studySession);

    UpdateStudySessionRequest fromUpdateStudySessionRequestDto(UpdateStudySessionRequestDto dto);

    DeleteStudySessionRequest fromDeleteStudySessionRequestDto(DeleteStudySessionRequestDto dto);
}
