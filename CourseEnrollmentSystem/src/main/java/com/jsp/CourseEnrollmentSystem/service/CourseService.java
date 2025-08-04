package com.jsp.CourseEnrollmentSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CourseEnrollmentSystem.dao.CourseDao;
import com.jsp.CourseEnrollmentSystem.dto.ResponseStructure;
import com.jsp.CourseEnrollmentSystem.entity.Course;
import com.jsp.CourseEnrollmentSystem.entity.Instructor;
import com.jsp.CourseEnrollmentSystem.exception.IdNotFoundException;
import com.jsp.CourseEnrollmentSystem.repository.InstructorRepository;

@RestController
@RequestMapping("/course")
public class CourseService {
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private InstructorRepository instructorRepository;	
	
	//save Course record 
	public ResponseEntity<ResponseStructure<Course>> saveCourse(int instructorId, Course course ){
		Optional<Instructor> opt= instructorRepository.findById(instructorId);
		
		if(opt.isEmpty()) {
			throw new IdNotFoundException();
		}
		
		course.setInstructor(opt.get());
		Course savedCourse =courseDao.saveCourse(course);
		
		ResponseStructure<Course>structure= new ResponseStructure<Course>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Course saved successfully");
		structure.setData(savedCourse);
		return new ResponseEntity<> (structure, HttpStatus.CREATED);
	}
	
	// Get All Course Information
	public ResponseEntity<ResponseStructure<List<Course>>> getAllCourseDetails(){
	List<Course> courses = courseDao.getAllCourseDetails();
	ResponseStructure<List<Course>> structure = new ResponseStructure<List<Course>>();
	structure.setStatusCode(HttpStatus.OK.value());
	structure.setMessage("Records Access Successfully");
	structure.setData(courses);
	return new ResponseEntity<ResponseStructure<List<Course>>>(structure, HttpStatus.OK);
	}
	
	// get Course By Id
	public ResponseEntity<ResponseStructure<Course>> getCourseById(int id){
		Optional<Course>optCourse =courseDao.getCourseById(id);
		ResponseStructure<Course>structure =new ResponseStructure<Course>();
		if(optCourse.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Course details Access by id Successfully");
			structure.setData(optCourse.get());
			return new ResponseEntity<ResponseStructure<Course>>(structure,HttpStatus.OK);
		}
		else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Course Id Not Exist");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Course>>(structure ,HttpStatus.NOT_FOUND);
		}
	}
	//Update Course
	public ResponseEntity<ResponseStructure<Course>> updateCourseById(Course course , int id ,int instructorID){
		Optional<Course> optCourse1= courseDao.getByIdCourse(id);
		Optional<Instructor> optInstructor = instructorRepository.findById(instructorID);
		ResponseStructure<Course> structure1= new ResponseStructure<Course>();
		if(optCourse1.isPresent() && optInstructor.isPresent()) {
			course.setId(id);
			course.setInstructor(optInstructor.get());
			Course updateCourse=courseDao.saveCourse(course);
			structure1.setStatusCode(HttpStatus.OK.value());
			structure1.setMessage("Update Succesfull");
			structure1.setData(updateCourse);
			return new ResponseEntity<ResponseStructure<Course>>(structure1,HttpStatus.OK);
		}
		else {
			structure1.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure1.setMessage("Course not found with Id :"+id);
			structure1.setData(null);
			return new ResponseEntity<>(structure1, HttpStatus.NOT_FOUND);
		}
	}
	
	// Delete course Details
	public ResponseEntity<ResponseStructure<String>> deleteCourseDetailsById(int id){
		Optional<Course> optCourse = courseDao.getByIdCourse(id);
		if(optCourse.isPresent()) {
			courseDao.deleteCourseDetailsById(optCourse.get());
			ResponseStructure<String> structure =new ResponseStructure<String>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Course deleted successfully");
			structure.setData("Deleted course with ID"+ id);
			return new ResponseEntity<>(structure ,HttpStatus.OK);
		}
			else {
				throw new IdNotFoundException();
			}
		}
	//Get Course BY InstructorId
	public ResponseEntity<ResponseStructure<List<Course>>>getCourseByInstructorId(int instructorId){
		List<Course> courses= courseDao.getCourseByInstructorId(instructorId);
		if(courses.isEmpty()){
			throw new IdNotFoundException();
		}
		ResponseStructure<List<Course>> structure= new ResponseStructure<List<Course>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Course Retrieved Succesfully Bu InstructorId");
		structure.setData(courses);
		return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.OK);
		
	}
	
	//Get Course By Pagination and Sorting
	public ResponseEntity<ResponseStructure<List<Course>>> getCourseWithPaginationAndSorting(int page, int size, String sortBy, String direction){
		Sort sort = direction.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		PageRequest pageable =PageRequest.of(page,size,sort);
		Page<Course> coursePage = courseDao.getAllCourse(pageable);
		
		List<Course>courses=coursePage.getContent();
		if(courses.isEmpty()) {
			throw new IdNotFoundException();
		}
		ResponseStructure<List<Course>> structure = new ResponseStructure<List<Course>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Course retrieved Successfully with Pagination and Sorting");
		structure.setData(courses);
		return new ResponseEntity<ResponseStructure<List<Course>>>(structure , HttpStatus.OK);
	}

}
