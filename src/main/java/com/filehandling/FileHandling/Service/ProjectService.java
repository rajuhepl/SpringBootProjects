package com.filehandling.FileHandling.Service;

import com.filehandling.FileHandling.Model.Project;
import com.filehandling.FileHandling.Model.SubTask;
import com.filehandling.FileHandling.Model.Task;
import com.filehandling.FileHandling.Repository.ProjectRepository;
import com.filehandling.FileHandling.Repository.SubTaskRepository;
import com.filehandling.FileHandling.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final String success="Api success" ;
    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private SubTaskRepository subrepo;

    @Autowired
   private TaskRepository taskrepo;


    //Add Project Data
    public String AddProject(Project project){
        for (Task task : project.getTask()) {
            subrepo.saveAll(task.getSubtask());
        }

        taskrepo.saveAll(project.getTask());
        projectRepo.save(project);
        return success;
    }

    public String AddAllProject(List<Project> project) {
        projectRepo.saveAll(project);
        return success;
    }
}
