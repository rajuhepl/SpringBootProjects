package com.filehandling.FileHandling.Repository;

import com.filehandling.FileHandling.Model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users,String> {
}
