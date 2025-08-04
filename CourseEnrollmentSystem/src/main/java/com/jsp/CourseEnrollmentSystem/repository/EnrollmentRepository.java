package com.jsp.CourseEnrollmentSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.CourseEnrollmentSystem.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository< Enrollment , Integer> {
	
	//Custom :Get Enrollment By StudentId
	List <Enrollment> findEnrollmentByStudentId(int studentId);
	
	// Custom : get Enrollment By CourseId
	List<Enrollment> findEnrollmentByCourseId(int courseId);

}
