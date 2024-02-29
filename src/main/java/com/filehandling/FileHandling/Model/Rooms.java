package com.filehandling.FileHandling.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="room")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int room_id;
    private String room_no;
    private String Size;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
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
