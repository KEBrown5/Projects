package Devtiro.Task.App.service;

import Devtiro.Task.App.domain.entity.Task;

import java.util.List;
import java.util.UUID;

import Devtiro.Task.App.domain.CreateTaskRequest;
import Devtiro.Task.App.domain.UpdateTaskRequest;

public interface TaskService {
    Task createTask(CreateTaskRequest request);

    List<Task> listTasks();

    Task updateTask(UUID taskId, UpdateTaskRequest request);

    void deleteTask(UUID taskId);
}
