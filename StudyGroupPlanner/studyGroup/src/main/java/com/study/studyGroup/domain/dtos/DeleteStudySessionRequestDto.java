package com.study.studyGroup.domain.dtos;

import java.util.UUID;

public class DeleteStudySessionRequestDto {
    public DeleteStudySessionRequestDto() {

    }

    public DeleteStudySessionRequestDto(UUID id) {
        this.id = id;
    }

    UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
