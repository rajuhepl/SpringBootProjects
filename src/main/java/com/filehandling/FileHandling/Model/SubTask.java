package com.filehandling.FileHandling.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subtask_id;
    private String subtask_name;

    public Long getSubtask_id() {
        return subtask_id;
    }

    public void setSubtask_id(Long subtask_id) {
        this.subtask_id = subtask_id;
    }

    public String getSubtask_name() {
        return subtask_name;
    }

    public void setSubtask_name(String subtask_name) {
        this.subtask_name = subtask_name;
    }
}
