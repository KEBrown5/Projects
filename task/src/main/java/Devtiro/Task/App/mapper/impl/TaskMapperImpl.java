package Devtiro.Task.App.mapper.impl;

import org.springframework.stereotype.Component;

import Devtiro.Task.App.domain.CreateTaskRequest;
import Devtiro.Task.App.domain.UpdateTaskRequest;
import Devtiro.Task.App.domain.dto.CreateTaskRequestDto;
import Devtiro.Task.App.domain.dto.TaskDto;
import Devtiro.Task.App.domain.dto.UpdateTaskRequestDto;
import Devtiro.Task.App.domain.entity.Task;
import Devtiro.Task.App.mapper.TaskMapper;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public CreateTaskRequest fromDto(CreateTaskRequestDto dto) {
        return new CreateTaskRequest(
            dto.title(),
            dto.description(),
            dto.dueDate(),
            dto.priority()
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.getPriority(),
            task.getStatus()
        );
    }

    @Override
    public UpdateTaskRequest fromDto(UpdateTaskRequestDto dto) {
        return new UpdateTaskRequest(
            dto.title(),
            dto.description(),
            dto.dueDate(),
            dto.status(),
            dto.priority()
        );
    }
}
