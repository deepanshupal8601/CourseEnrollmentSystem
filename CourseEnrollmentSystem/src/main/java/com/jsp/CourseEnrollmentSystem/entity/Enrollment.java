package com.jsp.CourseEnrollmentSystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)   // the primary key value will be generated automatically by Database
	private Integer id;
	private LocalDate enrolledDate;    // Date when the student enrolled in the course
	
	@ManyToOne
	@JoinColumn(name="student_id")   // Many enrollments can be associated with one student
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="course_id")    // Many enrollments can be associated with one course
	private Course course;
	
	//Getter And Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(LocalDate enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	


}
