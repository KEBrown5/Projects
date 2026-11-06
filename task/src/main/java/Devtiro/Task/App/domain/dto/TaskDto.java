package Devtiro.Task.App.domain.dto;

import java.time.LocalDate;
import java.util.UUID;

import Devtiro.Task.App.domain.entity.TaskPriority;
import Devtiro.Task.App.domain.entity.TaskStatus;

public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority,
    TaskStatus status
) {
    
}
