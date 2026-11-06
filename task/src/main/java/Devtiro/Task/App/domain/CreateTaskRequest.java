package Devtiro.Task.App.domain;

import java.time.LocalDate;

import Devtiro.Task.App.domain.entity.TaskPriority;


public record CreateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority
) {
    
}
    

