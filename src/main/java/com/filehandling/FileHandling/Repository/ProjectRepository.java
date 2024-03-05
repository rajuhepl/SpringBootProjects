package com.filehandling.FileHandling.Repository;

import com.filehandling.FileHandling.Model.Project;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project,String> {
}
