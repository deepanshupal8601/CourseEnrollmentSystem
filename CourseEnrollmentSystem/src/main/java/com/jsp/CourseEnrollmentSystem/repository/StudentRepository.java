package com.jsp.CourseEnrollmentSystem.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.CourseEnrollmentSystem.entity.Course;
import com.jsp.CourseEnrollmentSystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student ,Integer>{
	
	Optional<Student> save(int id);
	
	//get Course By Student Id
	@Query("Select e.course from Enrollment e where e.student.id=?1")
	List<Course> findCourseByStudentId(int studentId);
	
	//Find Student By Instructor Id
	@Query("Select e.student from Enrollment e Where e.course.instructor.id=:instructorID")
	List<Student>findStudentsByInstructorId(int instructorId);
	
    Page<Student>findAll(Pageable pageable);
	

}
