package com.jsp.CourseEnrollmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.CourseEnrollmentSystem.entity.Instructor;

public interface InstructorRepository  extends JpaRepository<Instructor , Integer>{

}
