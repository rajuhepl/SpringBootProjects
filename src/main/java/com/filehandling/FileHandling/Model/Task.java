package com.filehandling.FileHandling.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Document
public class Task {
    @Id
    private String task_id;
    private String task_name;
    @DocumentReference
    private List<SubTask> subtask;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public List<SubTask> getSubtask() {
        return subtask;
    }

    public void setSubtask(List<SubTask> subtask) {
        this.subtask = subtask;
    }
    // In the Task class constructor
    public Task() {
        this.subtask = new ArrayList<>();
    }

}
