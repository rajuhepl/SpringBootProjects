package com.filehandling.FileHandling.Repository;

import com.filehandling.FileHandling.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {


 /*   List<Task> findAllById(Long pId);*/
}
