package com.jsp.CourseEnrollmentSystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CourseEnrollmentSystem.dao.EnrollmentDao;
import com.jsp.CourseEnrollmentSystem.dto.ResponseStructure;
import com.jsp.CourseEnrollmentSystem.entity.Course;
import com.jsp.CourseEnrollmentSystem.entity.Enrollment;
import com.jsp.CourseEnrollmentSystem.entity.Student;
import com.jsp.CourseEnrollmentSystem.exception.IdNotFoundException;
import com.jsp.CourseEnrollmentSystem.repository.CourseRepository;
import com.jsp.CourseEnrollmentSystem.repository.StudentRepository;

@Service
public class EnrollmentService {
	@Autowired
	private EnrollmentDao enrollmentDao;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
    private CourseRepository courseRepository;
	
	//Save Enrollment Details
	public ResponseEntity<ResponseStructure<Enrollment>> saveEnrollment(int studentId , int courseId , Enrollment enrollment){
		 Optional<Student> optStudent = studentRepository.findById(studentId);
		if(optStudent.isEmpty()) {
			throw new IdNotFoundException();
		}
		Optional<Course>optCourse = courseRepository.findById(courseId);
		if(optCourse.isEmpty()) {
			throw new IdNotFoundException();
		}
		    enrollment.setCourse(optCourse.get());
	        enrollment.setStudent(optStudent.get());
		
		Enrollment saved = enrollmentDao.saveEnrollment(enrollment);
		
		ResponseStructure<Enrollment>structure= new ResponseStructure<Enrollment>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Enrollment Records inserted");
		structure.setData(saved);
		return new ResponseEntity<ResponseStructure<Enrollment>>(structure ,HttpStatus.CREATED);
		
	}
	// Get All Enrollment details
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getAllEnrollmentDetails(){
		List<Enrollment>enrollments = enrollmentDao.getAllEnrollmentDetails();
		ResponseStructure<List<Enrollment>> structure = new ResponseStructure<List<Enrollment>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Enrollment Details Access Successfully");
		structure.setData(enrollments);
		return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure,HttpStatus.OK);
	}
	
	//Get Enrollment by Id
	public ResponseEntity<ResponseStructure<Enrollment>> getEnrollmentById(int id){
		Optional<Enrollment> optEnrollment = enrollmentDao.getEnrollmentById(id);
		ResponseStructure<Enrollment> structure = new ResponseStructure<Enrollment>();
		if(optEnrollment.isPresent()) {
	       structure.setStatusCode(HttpStatus.OK.value());
	       structure.setMessage("Enrollment Details Access Succefully");
	       structure.setData(optEnrollment.get());
	       return new ResponseEntity<ResponseStructure<Enrollment>>(structure,HttpStatus.OK);
	       
		}
		else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Enrollment ID Not Exist");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Enrollment>>(structure, HttpStatus.OK);
		}
	}
	
	// Update Enrollment By Id
	public ResponseEntity<ResponseStructure<Enrollment>> updateByIdEnrollment(Enrollment enrollment, int id, int studentId, int courseId){
		Optional<Enrollment> optEnrollment = enrollmentDao.getEnrollmentById(id);
		Optional<Student> optStudent = studentRepository.findById(studentId);
		Optional<Course> optCourse =courseRepository.findById(courseId);
		ResponseStructure<Enrollment> structure1 = new ResponseStructure<Enrollment>();
		if(optEnrollment.isPresent()&& optStudent.isPresent()&& optCourse.isPresent()) {
			enrollment.setId(id);
			enrollment.setStudent(optStudent.get());
			enrollment.setCourse(optCourse.get());
			Enrollment updateEnrollment = enrollmentDao.saveEnrollment(enrollment);
			structure1.setStatusCode(HttpStatus.OK.value());
			structure1.setMessage("Update Succesfull");
			structure1.setData(updateEnrollment);
			return new ResponseEntity<>(structure1,HttpStatus.OK);
			
		}
		else {
			structure1.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure1.setMessage("Course not found with id"+id);
			structure1.setData(null);
			return new ResponseEntity<>(structure1,HttpStatus.NOT_FOUND);
		}
	}
	// Delete Course Details
	public ResponseEntity<ResponseStructure<String>> deleteEnrollmentDetailsById(int id){
		Optional<Enrollment>optEnrollment = enrollmentDao.getEnrollmentById(id);
		if(optEnrollment.isPresent()) {
			enrollmentDao.deleteEnrollmentDetailsById(optEnrollment.get());
			ResponseStructure<String> structure = new ResponseStructure<String>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Enrollment deleted Successfully");
			structure.setData("Deleted Enrollment with Id :"+id);
			return new ResponseEntity<>(structure, HttpStatus.OK);
			
		}
		else {
			throw new IdNotFoundException();
		}
	}
	// Get Enrollment By Student Id
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentByStudentId(int studentId){
		List<Enrollment> enrollments = enrollmentDao.getEnrollmentByStudentId(studentId);
		
		if(enrollments == null || enrollments.isEmpty()){
			throw new IdNotFoundException();
		}
		ResponseStructure<List<Enrollment>> structure= new ResponseStructure<List<Enrollment>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Enrollment Access Succesfully By Student Id");
		structure.setData(enrollments);
	    return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure, HttpStatus.OK); 
	}
	
	//Get Enrollment By CourseId
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentByCourseId(int courseId){
		List<Enrollment>enrollments = enrollmentDao.getEnrollmentByCourseId(courseId);
		if(enrollments==null || enrollments.isEmpty()) {
			throw new IdNotFoundException();
		}
		ResponseStructure<List<Enrollment>> structure = new ResponseStructure<List<Enrollment>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Enrollment Retrieved Succesfully By CourseId");
		structure.setData(enrollments);
		return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure,HttpStatus.OK);
		
	}
	//get Enrollment with Pagination and Sorting
	public ResponseEntity<ResponseStructure<Page<Enrollment>>> getEnrollmentWithPaginationAndSorting(Pageable pageable){
		Page<Enrollment> pageEnrollment = enrollmentDao.getEnrollment(pageable);
		ResponseStructure<Page<Enrollment>> structure = new ResponseStructure<Page<Enrollment>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Enrollment with Pagination and Sorting Succesfully");
		structure.setData(pageEnrollment);
		
		return new ResponseEntity<ResponseStructure<Page<Enrollment>>>(structure, HttpStatus.OK);
	}
	
	

}
