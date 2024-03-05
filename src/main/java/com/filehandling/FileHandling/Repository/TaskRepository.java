package com.filehandling.FileHandling.Repository;

import com.filehandling.FileHandling.Model.Task;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task,String> {


 /*   List<Task> findAllById(Long pId);*/
}
