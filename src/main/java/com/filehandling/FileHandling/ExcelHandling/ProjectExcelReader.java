package com.filehandling.FileHandling.ExcelHandling;

import com.filehandling.FileHandling.Model.Project;
import com.filehandling.FileHandling.Model.SubTask;
import com.filehandling.FileHandling.Model.Task;
import com.filehandling.FileHandling.Repository.ProjectRepository;
import com.filehandling.FileHandling.Repository.SubTaskRepository;
import com.filehandling.FileHandling.Repository.TaskRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectExcelReader {
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private SubTaskRepository subrepo;

    public void downloadprojectsAsExcel(HttpServletResponse response) throws IOException {
        List<Project> projectList = projectRepo.findAll(); // Fetch users from the database


        // Create a new Excel workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Projects.xlsx");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "ID"
                , "ProjectName"
                , "TaskName"
                ,"SubtaskName"
                }; // Adjust as per your User entity fields
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

       //Fill data Rows
        int rowNum = 1;
        for (Project project : projectList) {
            for (Task task : project.getTask()) {
                if (task.getSubtask().isEmpty()) {
                    // If there are no subtasks, create a row with empty SubtaskName
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(project.getP_id());
                    row.createCell(1).setCellValue(project.getProject_name());
                    row.createCell(2).setCellValue(task.getTask_name());
                    row.createCell(3).setCellValue(""); // Empty value for SubtaskName
                } else {
                    for (SubTask subTask : task.getSubtask()) {
                        Row row = sheet.createRow(rowNum++);
                        row.createCell(0).setCellValue(project.getP_id());
                        row.createCell(1).setCellValue(project.getProject_name());
                        row.createCell(2).setCellValue(task.getTask_name());
                        row.createCell(3).setCellValue(subTask.getSubtask_name());
                    }
                }
            }
        }


        // Set content type and header for the response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=project.xlsx");

        // Write the Excel workbook to the response output stream
        workbook.write(response.getOutputStream());
        workbook.close();
    }


    //Import Code
  /*  public List<Project> uploadProjectsFromExcel(List<MultipartFile> filePath) throws IOException {
        List<Project> ProjectList = new ArrayList<>();
        List<Task> tasklList = new ArrayList<>();
        List<SubTask> subtasklist = new ArrayList<>();


        filePath.stream().forEach(excelFile ->{
            Workbook workbook = null;
            try {
                workbook = new XSSFWorkbook(excelFile.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Sheet datatypeSheet = workbook.getSheetAt(0);

            for (Row currentRow : datatypeSheet) {
                if (currentRow.getRowNum() == 0) { // Skip header row
                    continue;
                }
                Project project = new Project();
                Task task = new Task();
                SubTask subTask = new SubTask();

                Cell projectIDCell = currentRow.getCell(0);
                if (projectIDCell != null) {
                    project.setP_id((long) projectIDCell.getNumericCellValue());
                }

                Cell projectNameCell = currentRow.getCell(1);
                if (projectNameCell != null) {
                    project.setProject_name(projectNameCell.getStringCellValue());
                }

                Cell taskNameCell = currentRow.getCell(2);


                if (taskNameCell != null) {
                    task.setTask_name(taskNameCell.getStringCellValue());
                }else {
                    task.setTask_name("");
                }




                Cell subTaskNameCell = currentRow.getCell(3);
                if (subTaskNameCell != null ) {
                    subTask.setSubtask_name(subTaskNameCell.getStringCellValue());
                }else {
                    subTask.setSubtask_name("");
                }

                // Save to database



                project.setTask(tasklList);


                projectRepo.save(project);




            }


        });


        return ProjectList;
    }*/



    @Transactional // Ensure all operations are transactional
    public List<Project> uploadProjectsFromExcel(List<MultipartFile> filePath) throws IOException {
        List<Project> projectList = new ArrayList<>();

        for (MultipartFile file : filePath) {
            try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
                Sheet sheet = workbook.getSheetAt(0);

                for (Row currentRow : sheet) {
                    if (currentRow.getRowNum() == 0) { // Skip header row
                        continue;
                    }

                    // Retrieve project details from the Excel row
                    long projectId = (long) currentRow.getCell(0).getNumericCellValue();
                    String projectName = currentRow.getCell(1).getStringCellValue();

                    // Retrieve task details from the Excel row
                    String taskName = currentRow.getCell(2).getStringCellValue();

                    // Retrieve subtask details from the Excel row
                    String subtaskName = currentRow.getCell(3).getStringCellValue();

                    // Find the project in the list or create a new one
                    Optional<Project> optionalProject = projectList.stream()
                            .filter(project -> project.getP_id() == projectId)
                            .findFirst();

                    Project project = optionalProject.orElseGet(() -> {
                        Project newProject = new Project();
                        newProject.setP_id(projectId);
                        newProject.setProject_name(projectName);
                        projectList.add(newProject);
                        return newProject;
                    });

                    // Find the task in the project or create a new one
                    Optional<Task> optionalTask = project.getTask().stream()
                            .filter(task -> task.getTask_name().equals(taskName))
                            .findFirst();

                    Task task = optionalTask.orElseGet(() -> {
                        Task newTask = new Task();
                        newTask.setTask_name(taskName);
                        project.getTask().add(newTask);
                        return newTask;
                    });

                    // Create a new subtask and add it to the task
                    SubTask subTask = new SubTask();
                    subTask.setSubtask_name(subtaskName);
                    task.getSubtask().add(subTask);
                }
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log the exception
            }
        }

        // Save projects to the database
        projectRepo.saveAll(projectList);
        return projectList;
    }




}
