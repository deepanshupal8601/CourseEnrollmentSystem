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
import com.jsp.CourseEnrollmentSystem.entity.Instructor;
import com.jsp.CourseEnrollmentSystem.entity.Student;
import com.jsp.CourseEnrollmentSystem.service.InstructorService;

@RequestMapping("/instructor")
@RestController
public class InstructorController {
	@Autowired
	private InstructorService instructorService;
	
	// save Instructor Record
	@PostMapping                  //http://localhost:8080/instructor + JSON 
	public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(@RequestBody Instructor instructor){
		return instructorService.saveInstructor(instructor);
		
	}
	
	// Get All Instructor Details
	@GetMapping                        //http://localhost:8080/instructor
	public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructorDetails(){
		return instructorService.getAllInstructorDetails();
		
	}
	
	//Get Instructor By Id      
	@GetMapping("/{id}")           //http://localhost:8080/instructor/2
	public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(@PathVariable int id){
		return instructorService.getInstructorById(id);
	}
	
	//Update Instructor     
	@PutMapping("/{id}")     // http://localhost:8080/instructor/2  + JSON
	public ResponseEntity<ResponseStructure<Instructor>> updateByIdInstructor(@RequestBody Instructor instructor , @PathVariable int id ){
	  return instructorService.updateByIdInstructor(instructor  , id);
	
	}	
	
	// Delete Instructor by Id                //http://localhost:8080/instructor/1
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteInstructorDetailsById(@PathVariable int id) {
	    return instructorService.deleteInstructorDetailsById(id);
	}


	//Get Students By Instructor id             http://localhost:8080/instructor/3/students
	@GetMapping("/{id}/students")
	public ResponseEntity<ResponseStructure<List<Student>>> getStudentsByInstructorId(@PathVariable int id){
		return instructorService.getStudentsByInstructorId(id);
	}
	
	
	// Controller method signature
	@GetMapping("/filtered")      //http://localhost:8080/instructor/filtered?page=0&size=5&sorting=id&direction=asc
	public ResponseEntity<ResponseStructure<Page<Instructor>>> getInstructorWithPaginationAndSorting(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id") String sorting,
	        @RequestParam(defaultValue = "ase") String direction) {
	    return instructorService.getInstructorWithPaginationAndSorting(page, size, sorting, direction);
	}

	

}

