package com.filehandling.FileHandling.Service;

import com.filehandling.FileHandling.Model.Project;
import com.filehandling.FileHandling.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final String success="Api success" ;
    @Autowired
    private ProjectRepository projectRepo;


    //Add Project Data
    public String AddProject(Project project){
        projectRepo.save(project);
        return success;
    }

    public String AddAllProject(List<Project> project) {
        projectRepo.saveAll(project);
        return success;
    }
}
