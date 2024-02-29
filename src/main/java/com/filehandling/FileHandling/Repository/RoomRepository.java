package com.filehandling.FileHandling.Repository;

import com.filehandling.FileHandling.Model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Rooms,Integer> {
}
