package com.filehandling.FileHandling.Repository;

import com.filehandling.FileHandling.Model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTask,Long> {
   /* List<SubTask> findAllById(Long taskId);*/
}
