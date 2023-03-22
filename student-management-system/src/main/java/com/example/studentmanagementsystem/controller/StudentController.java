package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/new")
    public  String createStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "createStudent";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable UUID id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "editStudent";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable UUID id,
                                @ModelAttribute("student") Student student,
                                Model model) {

        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

}
