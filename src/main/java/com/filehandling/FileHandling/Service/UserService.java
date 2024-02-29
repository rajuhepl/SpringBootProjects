package com.filehandling.FileHandling.Service;

import com.filehandling.FileHandling.Model.Users;
import com.filehandling.FileHandling.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final String success="Api success" ;

    @Autowired
    private UserRepository userRepository;

    public String saveUsers(List<Users> users) {
        userRepository.saveAll(users);
        return success;
    }

    public String saveUsers(Users user) {
        userRepository.save(user);
        return success;
    }
}
