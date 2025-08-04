package com.jsp.CourseEnrollmentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.jsp.CourseEnrollmentSystem.service.CourseService;

@RequestMapping("/course")
@RestController
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	// save Course 
	
	@PostMapping          //   http://localhost:8080/course?instructorId=1  +  and in Body(Raw->JSON) i will write JSON
	public ResponseEntity<ResponseStructure<Course>> saveCourse(@RequestParam int instructorId, @RequestBody Course course){
		return courseService.saveCourse(instructorId , course);
	}
	
	//Get All Records
	
	@GetMapping             //http://localhost:8080/course
	public ResponseEntity<ResponseStructure<List<Course>>>getAllCourseDetails(){
		return courseService.getAllCourseDetails();
	}
	
	//Get Course Details By Id
	
	@GetMapping("/{id}")        //http://localhost:8080/course/3
	public  ResponseEntity<ResponseStructure<Course>> getCourseById(@PathVariable int id){
		return courseService.getCourseById(id);
		
	}
	//Update Course 
	
	@PutMapping("/{id}")    //http://localhost:8080/course/3?instructor_id=1 +  and in Body(Raw->JSON) i will write JSON
	public ResponseEntity<ResponseStructure<Course>> updateCourseById(@RequestBody Course course, @PathVariable int id , @RequestParam int instructor_id){
		return courseService.updateCourseById(course ,id, instructor_id);
	}
	
	// Delete Course
	
    @DeleteMapping("/{id}")       //http://localhost:8080/course/3
    public ResponseEntity<ResponseStructure<String>> deleteCourseDetailsById(@PathVariable int id) {
        return courseService.deleteCourseDetailsById(id);
    }
	
	//Get course By Instructor Id
    
	@GetMapping("/instructor/{instructorId}")       //http://localhost:8080/course/instructor/1
	public ResponseEntity<ResponseStructure<List<Course>>> getCourseByInstructorId (@PathVariable int instructorId){
		return courseService.getCourseByInstructorId(instructorId);
	}
	
	
	//Get Course with Pagination And Sorting 
	
	@GetMapping("/filtered")           //http://localhost:8080/course/filtered?page=0&size=5&sortBy=name&direction=asc
	public ResponseEntity<ResponseStructure<List<Course>>> getCourseWithPaginationAndsorting(
		@RequestParam(defaultValue="0") int page,
		@RequestParam(defaultValue="5") int  size,
		@RequestParam(defaultValue = "id") String sortBy,
		@RequestParam(defaultValue= "asc") String direction){
		
		return courseService.getCourseWithPaginationAndSorting(page, size, sortBy, direction);
		
	}
	

}
