package com.jsp.CourseEnrollmentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CourseEnrollmentSystem.dto.ResponseStructure;
import com.jsp.CourseEnrollmentSystem.entity.Course;
import com.jsp.CourseEnrollmentSystem.entity.Student;
import com.jsp.CourseEnrollmentSystem.service.StudentService;

@RestController
@RequestMapping("/student") 
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	// Saved Student Record
	@PostMapping               //http://localhost:8080/student + JSON
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student){
		return studentService.saveStudent(student);
	}
	
	//Get All Student Records
	@GetMapping                //http://localhost:8080/student
	public ResponseEntity<ResponseStructure<List<Student>>> getAllStudent(){
		return studentService.getAllStudent();
	}
	
	//Get Student By Id
	@GetMapping("/{id}")        //http://localhost:8080/student/1
	public ResponseEntity<ResponseStructure<Student>> getStudentByID(@PathVariable int id){
		return studentService.getStudentById(id);
	}
	
	//Update Student Records
	@PutMapping("/{id}")          //http://localhost:8080/student/1   + JSON
	public ResponseEntity<ResponseStructure<Student>>updatedStudentById(@RequestBody Student student, @PathVariable int id){
		return studentService.updateStudentById(student, id);
	}
	
	//Delete Student
	@DeleteMapping("/{id}")          //http://localhost:8080/studnet/3
	public ResponseEntity<ResponseStructure<String>> deleteStudentDetailsById(@PathVariable int id){
		return studentService.deleteStudentDetailsById(id);
	}
	// Get Course by Student Id       // http://localhost:8080/student/1/courses
	@GetMapping("/{studentId}/courses")
	public ResponseEntity<ResponseStructure<List <Course>>>getCourseByStudentId(@PathVariable int studentId){
		return studentService.getCourseByStudentId(studentId);
		
	}
	// Get Student With Pagination & sorting       //http://localhost:8080/student/all?page=0&size=5&sortBy=email
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<Page<Student>>>getStudents(
			@RequestParam(defaultValue ="0") int page,
			@RequestParam(defaultValue="3") int size,
			@RequestParam(defaultValue="id") String sortBy){
		   return studentService.getStudents(page, size, sortBy);
	}
	
	

}
