package com.filehandling.FileHandling.Model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
public class SubTask {
    @Id
    private String subtask_id;
    private String subtask_name;


    public String getSubtask_id() {
        return subtask_id;
    }

    public void setSubtask_id(String subtask_id) {
        this.subtask_id = subtask_id;
    }

    public String getSubtask_name() {
        return subtask_name;
    }

    public void setSubtask_name(String subtask_name) {
        this.subtask_name = subtask_name;
    }
}
