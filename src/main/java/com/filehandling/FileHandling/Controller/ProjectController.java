package com.filehandling.FileHandling.Controller;

import com.filehandling.FileHandling.ExcelHandling.ProjectExcelReader;
import com.filehandling.FileHandling.Model.Project;
import com.filehandling.FileHandling.Model.Users;
import com.filehandling.FileHandling.Service.ProjectService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectExcelReader projectExcelReader;

    @PostMapping("/project")
    public String addProject (@RequestBody Project project){
        return projectService.AddProject(project);
    }

    @GetMapping("/projectdownload")
    public ResponseEntity<?> downloadProject(HttpServletResponse response) {
        try {
            projectExcelReader.downloadprojectsAsExcel(response);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to download users: " + e.getMessage());
        }
    }
    @PostMapping("/upload")
    public List<Project> uploadProject(@RequestPart("file") List<MultipartFile> file) throws IOException {

        List<Project> Project = projectExcelReader.uploadProjectsFromExcel(file);

        return  Project;
    }

}
