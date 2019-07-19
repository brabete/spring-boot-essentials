package com.yourname.demo.reource;

import com.yourname.demo.model.Student;
import com.yourname.demo.service.StudentService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/students")
public class StudentResource {

  private final StudentService studentService;

  @Autowired
  public StudentResource(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public List<Student> getAllStudents() {
    return studentService.getAllStudents();
  }

  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      path = "{studentId}"
  )
  public Student getStudentById(@PathVariable("studentId") UUID studentId) {
    return studentService.getStudentById(studentId);
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public void insertNewStudent(@RequestBody Student student) {
    studentService.persistNewStudent(UUID.randomUUID(), student);
  }

  @PutMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      path = "{studentId}"
  )
  public void updateStudent(@PathVariable("studentId") UUID studentId, @RequestBody Student student) {
    studentService.updateStudentById(studentId, student);
  }

  @DeleteMapping(
      path = "{studentId}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public void deleteStudent(@PathVariable("studentId") UUID studentId) {
    studentService.deleteStudentById(studentId);
  }

}
