package com.filehandling.FileHandling.Controller;

/*mport com.filehandling.FileHandling.ExcelHandling.ExcelReader;*/
import com.filehandling.FileHandling.ExcelHandling.ExcelReader;
import com.filehandling.FileHandling.Model.Users;
import com.filehandling.FileHandling.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ExcelReader excelReader;

    @PostMapping("/upload")
    public String uploadUsers(@RequestPart("file") List<MultipartFile> file) throws IOException {

        List<Users> users = excelReader.readUsersFromExcel( file);

       return  userService.saveUsers(users);
    }

    @PostMapping("/user")
    public String useradd(@RequestBody Users user){
        return userService.saveUsers(user);
    }
    @GetMapping("/download")
    public ResponseEntity<?> downloadUsers(HttpServletResponse response) {
        try {
            excelReader.downloadUsersAsExcel(response);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to download users: " + e.getMessage());
        }
    }

}