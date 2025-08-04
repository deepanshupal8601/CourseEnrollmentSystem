package com.jsp.CourseEnrollmentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.jsp.CourseEnrollmentSystem.entity.Enrollment;
import com.jsp.CourseEnrollmentSystem.service.EnrollmentService;

@RequestMapping("/enrollment")
@RestController
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentService;
	
	//Save Enrollment Records
	@PostMapping                       //http://localhost:8080/enrollment?studentId=1&courseId=1 + Body(Raw-->JSON)
	public ResponseEntity<ResponseStructure<Enrollment>> saveEnrollment(@RequestParam int  studentId, @RequestParam int courseId, @RequestBody Enrollment enrollment){
		return enrollmentService.saveEnrollment(studentId , courseId, enrollment);
	}
	
	// Get Enrollment Details
	@GetMapping("/{id}")                 //http://localhost:8080/enrollment/21
	public ResponseEntity<ResponseStructure<Enrollment>> getEnrollmentById(@PathVariable int id){
		return enrollmentService.getEnrollmentById(id);
	}
	
	//Update Enrollment 
	@PutMapping("/{id}")            //PUT → http://localhost:8080/enrollment/21?student_id=1&course_Id=1
	public ResponseEntity<ResponseStructure<Enrollment>> updateByIdEnrollment(@RequestBody Enrollment enrollment,
			                                                                @PathVariable int id, @RequestParam("student_id") int studentId,
	                                                                        @RequestParam("course_Id") int courseId){
		return enrollmentService.updateByIdEnrollment(enrollment , id, studentId, courseId);
	}
	
	// Delete Enrollment BY Id
	@DeleteMapping("/{id}")              //DELETE  http://localhost:8080/enrollment/21
	public ResponseEntity<ResponseStructure<String>> deleteEnrollmentDetailsById(@PathVariable int id){
		return enrollmentService.deleteEnrollmentDetailsById(id);
	}
	
	// Get Enrollment By Student Id             //http://localhost:8080/enrollment/studentId/3
	@GetMapping("/studentId/{studentId}")
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentByStudentId(@PathVariable int studentId){
		return enrollmentService.getEnrollmentByStudentId(studentId);
	}
	
	// Get Enrollment By course id         //---> http://localhost:8080/enrollment/courseId/1
	@GetMapping("courseId/{courseId}")
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentByCourseId(@PathVariable int courseId){
		return enrollmentService.getEnrollmentByCourseId(courseId);
	}
	
	// get Enrollment with Pagination and sorting     //http://localhost:8080/enrollment/filter?page=0&&size=5&sort=id,asc
	@GetMapping("/filter")
	public ResponseEntity<ResponseStructure<Page<Enrollment>>> getenrollmentPaginationAndSorting(Pageable pageable){
		return enrollmentService.getEnrollmentWithPaginationAndSorting(pageable);
	}

}
