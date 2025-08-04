package com.jsp.CourseEnrollmentSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.CourseEnrollmentSystem.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository <Course ,Integer> {
	Optional<Course> save(int id);
	//Get Course By Instructor Id
	List<Course> findByInstructorId(int instructorId);
	

}
