package com.filehandling.FileHandling.Repository;

import com.filehandling.FileHandling.Model.Rooms;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Rooms,String> {
}
