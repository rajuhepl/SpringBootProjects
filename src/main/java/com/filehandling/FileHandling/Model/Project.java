package com.filehandling.FileHandling.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long p_id;
    private String project_name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="project_id" , referencedColumnName = "p_id")
    private List<Task> task;

    public Long getP_id() {
        return p_id;
    }

    public void setP_id(Long p_id) {
        this.p_id = p_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
    // In the Project class constructor
    public Project() {
        this.task = new ArrayList<>();
    }

}
