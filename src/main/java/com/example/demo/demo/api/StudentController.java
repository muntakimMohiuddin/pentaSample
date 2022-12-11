package com.example.demo.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.demo.model.Request;
import com.example.demo.demo.model.Student;
import com.example.demo.demo.service.*;
import javax.validation.Valid;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/me")
    public Student getCurrentUser(@RequestBody @Valid  HashMap<String,String> body) {
        String id=body.get("id");
        return studentService.getStudent(UUID.fromString(id));
    }

    @PostMapping("/request")
    public boolean makeRequest(@RequestBody @Valid Request request) {
        System.out.println(request.toString());
        // return true;
        return studentService.makeRequest(request.studentId,
                request.advisorId);
    }

    @PutMapping("/edit_profile")
    public Student editProfile(@RequestBody @Valid Student student) {
        System.out.println(student.toString());
        // return student;
        // Student student=new Student("edited_name", "01983620418","muntakimm54@gmail.com",UUID.fromString("82fc1800-d199-48a3-b70d-7ed3e7eb5915"),"editted_department",null);
        return studentService.editProfile(student);
    }
    @PostMapping("/change_password")
    public boolean changePassword(@RequestBody @Valid HashMap<String,String> body ) {
        String oldPassword=body.get("oldPassword");
        String newPassword=body.get("newPassword");
        String id=body.get("id");
        System.out.println(id+oldPassword+newPassword);
        // return false;
        return studentService.changePassword(UUID.fromString(id),oldPassword,newPassword);
    }
}