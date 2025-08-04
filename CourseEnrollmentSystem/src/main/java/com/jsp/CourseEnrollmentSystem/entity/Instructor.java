package com.jsp.CourseEnrollmentSystem.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Instructor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)    // the primary key value will be generated automatically by Database
	private Integer id;
	private String name;
	private String email;
	private String specialization;
	
	@JsonIgnore
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="instructor")
	// One instructor can have multiple courses
	// CascadeType.ALL means all operations (persist, remove, etc.) will cascade to courses
	// mappedBy="instructor" indicates this is the inverse side of the relationship, linked by 'instructor' in Course entity
	
	private List<Course> courses;
	
    //Getters And Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	

}
