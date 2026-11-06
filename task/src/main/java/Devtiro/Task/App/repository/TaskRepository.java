package Devtiro.Task.App.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Devtiro.Task.App.domain.entity.Task;

// By extending JpaRepository, our task repository automatically inherits
// many useful functions, such as find by id, update, etc

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID>{

} 
