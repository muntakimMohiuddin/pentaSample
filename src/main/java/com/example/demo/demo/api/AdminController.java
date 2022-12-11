package com.example.demo.demo.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.demo.model.Student;
import com.example.demo.demo.model.Teacher;
import  com.example.demo.demo.service.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/add_student")
    public boolean addStudent(@RequestBody @Valid Student student) {
    //     System.out.println(student.toString());
    // // Student student=new Student("muntakim", "01983620416", 
    // // "muntakim54@gmail.com", UUID.randomUUID(), "CSE",null);
    // System.out.println(student);
    // return false;
    return adminService.addStudent(student);
    }
    @PostMapping("/add_teacher")
    public boolean addTeacher(@RequestBody @Valid Teacher teacher) {
        // System.out.println(teacher.toString());
    // Teacher teacher=new Teacher("teacher1", "01111111111", 
    // "teacher1@gmail.com", UUID.randomUUID(), "CSE");
    // System.out.println(teacher);
    // return false;
    return adminService.addTeacher(teacher);
    }
    @PostMapping("/deactivate_student/{id}")
    public boolean deactivateStudent(@PathVariable("id") UUID id){
    return adminService.deactivateStudent(id);
    }
    @PostMapping("/deactivate_teacher/{id}")
    public boolean deactivateTeacher(@PathVariable("id") UUID id){
    return adminService.deactivateTeacher(id);
    }
    @GetMapping("/get_student")
    public List<Student> getStudent(){
        System.out.println("admin get student");
    return adminService.selectAllStudents();
    }
    @GetMapping("/get_teacher")
    public List<Teacher> getTeacher(){
    return adminService.selectAllTeachers();
    }
}
