package com.filehandling.FileHandling.Repository;

import com.filehandling.FileHandling.Model.SubTask;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubTaskRepository extends MongoRepository<SubTask,String> {
   /* List<SubTask> findAllById(Long taskId);*/
}
