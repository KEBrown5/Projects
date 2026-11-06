package com.study.studyGroup.domain.dtos;

import java.util.UUID;

public class UpdatePasswordResponseDto {

    public UpdatePasswordResponseDto() {

    }

    public UpdatePasswordResponseDto(UUID id) {
        this.id = id;
    }

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
