package com.jsp.CourseEnrollmentSystem.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CourseEnrollmentSystem.entity.Course;
import com.jsp.CourseEnrollmentSystem.entity.Student;
import com.jsp.CourseEnrollmentSystem.repository.StudentRepository;

@Repository
public class StudentDao {
	@Autowired
	
	private StudentRepository studentRepository;
	
	//Save Student Records
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	
	//Get All Student By Id
	public List<Student> getAllStudent(){
		return studentRepository.findAll();
	}
	
	//Get student By Id
	public Optional<Student> getStudentById(int id){
		return studentRepository.findById(id);
	}
	
	//Update Student Details
	public Optional<Student> getByIdStudent(int id){
		return studentRepository.findById(id);
	}
	//Delete Student Record By Id
	public void deleteStudentDetailsById(Student student) {
		studentRepository.delete(student);
	}
	
	//Get  Course By Student Id
	public List<Course> getCourseByStudentId(int studentId){
		return studentRepository.findCourseByStudentId(studentId);
	}
	
	
	// Get Student using Pagination And Sorting
	public Page<Student> getStudents(Pageable pageable){
		return studentRepository.findAll(pageable);
		
	}

}
