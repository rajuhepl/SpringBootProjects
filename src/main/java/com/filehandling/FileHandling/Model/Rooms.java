package com.filehandling.FileHandling.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Rooms {
    @Id
    private String room_id;
    private String room_no;
    private String Size;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }


}
