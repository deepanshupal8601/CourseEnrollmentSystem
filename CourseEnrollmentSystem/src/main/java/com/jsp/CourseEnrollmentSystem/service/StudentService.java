package com.jsp.CourseEnrollmentSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable; // Sahi import for Pageable
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CourseEnrollmentSystem.dao.StudentDao;
import com.jsp.CourseEnrollmentSystem.dto.ResponseStructure;
import com.jsp.CourseEnrollmentSystem.entity.Course;
import com.jsp.CourseEnrollmentSystem.entity.Student;
import com.jsp.CourseEnrollmentSystem.exception.IdNotFoundException;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	
	// Save Student Record 
	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student){
		Student saved = studentDao.saveStudent(student);
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Records saved Successfully");
		structure.setData(saved);
		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
	}
	
	// Get All Students
    public ResponseEntity<ResponseStructure<List<Student>>> getAllStudent(){
    	List<Student> student = studentDao.getAllStudent();
    	ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
    	structure.setStatusCode(HttpStatus.OK.value());
    	structure.setMessage("Student Records Accessed Successfully");
    	structure.setData(student);
    	return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.OK);
    }
    
    // Get Student By Id
    public ResponseEntity<ResponseStructure<Student>> getStudentById(int id){
    	Optional<Student> optStudent = studentDao.getStudentById(id);
    	ResponseStructure<Student> structure = new ResponseStructure<Student>();
    	if(optStudent.isPresent()) {
    		structure.setStatusCode(HttpStatus.OK.value());
    		structure.setMessage("Student Details Accessed By Id Successfully");
    		structure.setData(optStudent.get());
    		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
    	} else {
    		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
    		structure.setMessage("Student Id Not Exist");
    		structure.setData(null);
    		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.NOT_FOUND);
    	}
    }
    
    // Update Student Record By Id
    public ResponseEntity<ResponseStructure<Student>> updateStudentById(Student student, int id){
    	
    	Optional<Student> optStudent = studentDao.getByIdStudent(id);
    	ResponseStructure<Student> structure =  new ResponseStructure<>();
    	
    	if(optStudent.isPresent()) {
    		student.setId(id);
    		Student updatedStudent = studentDao.saveStudent(student);
    		structure.setStatusCode(HttpStatus.OK.value());
    		structure.setMessage("Update Successful");
    		structure.setData(updatedStudent);
    		return new ResponseEntity<>(structure, HttpStatus.OK);
    	} else {
    		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
    		structure.setMessage("Student not found with ID: " + id);
    		structure.setData(null);
    		return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    	}
    }
    
    // Delete Student details By ID
    public ResponseEntity<ResponseStructure<String>> deleteStudentDetailsById(int id){
    	Optional<Student> optStudent = studentDao.getStudentById(id);
    	if(optStudent.isPresent()) {
    		studentDao.deleteStudentDetailsById(optStudent.get());
    		ResponseStructure<String> structure = new ResponseStructure<>();
    		structure.setStatusCode(HttpStatus.OK.value());
    		structure.setMessage("Student deleted successfully");
    		structure.setData("Deleted Student with ID: " + id); 
    		return new ResponseEntity<>(structure, HttpStatus.OK);
    	} else {
    		throw new IdNotFoundException();
    	}
    }
    
    // Get Course By Student ID
    public ResponseEntity<ResponseStructure<List<Course>>> getCourseByStudentId(int studentId){
    	List<Course> course = studentDao.getCourseByStudentId(studentId);  
    	if(course.isEmpty()) {
    		throw new IdNotFoundException();
    	} else {
    		ResponseStructure<List<Course>> structure = new ResponseStructure<List<Course>>();
    		structure.setStatusCode(HttpStatus.OK.value());
    		structure.setMessage("Course fetched by student Id Successfully");
    		structure.setData(course);
    		return new ResponseEntity<ResponseStructure<List<Course>>>(structure, HttpStatus.OK);
    	}
    }
    
    // Get All Students With Pagination and Sorting
    public ResponseEntity<ResponseStructure<Page<Student>>> getStudents(int page, int size, String sortBy){
    	Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    	Page<Student> studentPage = studentDao.getStudents(pageable);
    	
    	ResponseStructure<Page<Student>> structure = new ResponseStructure<>();
    	structure.setStatusCode(HttpStatus.OK.value());
    	structure.setMessage("Student fetched Successfully with Pagination and Sorting");
    	structure.setData(studentPage);  
    	return new ResponseEntity<>(structure, HttpStatus.OK);
    }
}
