package com.example.demo.demo.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.demo.model.Request;
import com.example.demo.demo.model.Student;
import com.example.demo.demo.model.Teacher;
import  com.example.demo.demo.service.*;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/teacher")
public class TeacherController { 
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @GetMapping("/me")
    public Teacher getCurrentUser(@RequestBody @Valid  HashMap<String,String> body) {
        String id=body.get("id");
        System.out.println(id);
        return teacherService.getTeacher(UUID.fromString(id));
    }
    @GetMapping("/students")
    public List<Student> getStudents(@RequestBody @Valid  HashMap<String,String> body) {
        String id=body.get("id");
        System.out.println(id);
        return teacherService.getAllStudents(UUID.fromString(id));
    }

    @PostMapping("/request")
    public boolean makeRequest(@RequestBody @Valid HashMap<String,String> body) {
        String advisorId=body.get("advisorId");
        String studentId=body.get("studentId");
        boolean accept=body.get("accept")=="true";
        // System.out.println(new Request(UUID.fromString(advisorId),UUID.fromString(studentId),accept).toString()+accept);
        // return false;
        return teacherService.takeActionOnRequest( UUID.fromString(advisorId),UUID.fromString(studentId),accept);
    }

    @PutMapping("/edit_profile")
    public Teacher editProfile(@RequestBody @Valid Teacher teacher) {
        System.out.println(teacher.toString());
        // return teacher;
        // Teacher teacher=new Teacher("edited_teacher_name", "01983620420","teacher1_edited@gmail.com",UUID.fromString("482375cd-cbf1-43bd-8f4a-dd040743f361"),"editted_department");
        return teacherService.editProfile(teacher);
    }
    @PostMapping("/remove_student")
    public boolean removeStudent(@RequestBody @Valid HashMap<String,String> body) {
        String advisorId=body.get("advisorId");
        String studentId=body.get("studentId");
        return teacherService.removeStudentFromAdvisorList(UUID.fromString(advisorId),UUID.fromString(studentId));
    }
    @PostMapping("/change_password")
    public boolean changePassword(@RequestBody @Valid HashMap<String,String> body ) {
        String oldPassword=body.get("oldPassword");
        String newPassword=body.get("newPassword");
        String id=body.get("id");
        System.out.println(id+oldPassword+newPassword);
        // return false;
        return teacherService.changePassword(UUID.fromString(id),"01111111111","01111111112");
    }
}
