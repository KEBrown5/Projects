package Devtiro.Task.App.mapper;

import Devtiro.Task.App.domain.CreateTaskRequest;
import Devtiro.Task.App.domain.UpdateTaskRequest;
import Devtiro.Task.App.domain.dto.CreateTaskRequestDto;
import Devtiro.Task.App.domain.dto.TaskDto;
import Devtiro.Task.App.domain.dto.UpdateTaskRequestDto;
import Devtiro.Task.App.domain.entity.Task;


public interface TaskMapper {
    CreateTaskRequest fromDto(CreateTaskRequestDto dto);

    UpdateTaskRequest fromDto(UpdateTaskRequestDto dto);

    TaskDto toDto(Task task);
}
