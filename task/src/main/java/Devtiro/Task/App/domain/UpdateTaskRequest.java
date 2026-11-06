package Devtiro.Task.App.domain;

import java.time.LocalDate;

import Devtiro.Task.App.domain.entity.TaskPriority;
import Devtiro.Task.App.domain.entity.TaskStatus;

public record UpdateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskStatus status,
    TaskPriority priority
) {
    
}
