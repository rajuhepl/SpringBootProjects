package com.filehandling.FileHandling.ExcelHandling;

import com.filehandling.FileHandling.Model.Rooms;
import com.filehandling.FileHandling.Model.Users;
import com.filehandling.FileHandling.Repository.RoomRepository;
import com.filehandling.FileHandling.Repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelReader {

    @Autowired
    private UserRepository userRepository;// Assuming UserRepository exists

    @Autowired
    private RoomRepository roomRepo;
    public void downloadUsersAsExcel(HttpServletResponse response) throws IOException {
        List<Users> userList = userRepository.findAll(); // Fetch users from the database

        // Create a new Excel workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users.xlsx");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Name", "Email","Phone_No","room_no","roomSize"}; // Adjust as per your User entity fields
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Fill data rows
        int rowNum = 1;
        for (Users user : userList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getEmail());
            row.createCell(3).setCellValue(user.getPhone_number());
            row.createCell(4).setCellValue(user.getRoom().getRoom_no());
            row.createCell(5).setCellValue(user.getRoom().getSize());
        }

        // Set content type and header for the response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

        // Write the Excel workbook to the response output stream
        workbook.write(response.getOutputStream());
        workbook.close();
    }


    public List<Users> readUsersFromExcel(List<MultipartFile> filePath) {
        List<Users> userList = new ArrayList<>();
        filePath.stream().forEach(file ->{
            try {
                Workbook workbook = new XSSFWorkbook(file.getInputStream());
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                while (rowIterator.hasNext()){
                    Row row = rowIterator.next();
                    Users user = new Users();
                    user.setId(row.getCell(0).getStringCellValue());
                    user.setName(row.getCell(1).getStringCellValue());
                    user.setEmail(row.getCell(2).getStringCellValue());
                    user.setPhone_number(String.valueOf((long)row.getCell(3).getNumericCellValue()));
                    Rooms room = new Rooms();
                    room.setRoom_no(String.valueOf((int)(row.getCell(4).getNumericCellValue())));
                    room.setSize(row.getCell(5).getStringCellValue());
                    roomRepo.save(room);
                    user.setRoom(room);

                    userList.add(user);

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return userList;
    }

}
