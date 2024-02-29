package com.filehandling.FileHandling.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String task_name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id" , referencedColumnName = "task_id")
    private List<SubTask> subtask;

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
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
