package com.example.StudentsDao.Studetns;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "list")
    public List<Student> list(){
        return studentService.list();
    }

    @PostMapping(path = "item")
    public List<Student> add(@RequestBody Student student) {
        studentService.add(student);
        return studentService.list();
    }

    @DeleteMapping(path = "item/{studentId}")
    public void delete(@PathVariable(name = "studentId") Long studentId) {
        studentService.delete(studentId);
    }

    @PutMapping(path = "item")
    public void update(@RequestBody Student student){
        studentService.update(student);
    }
}
