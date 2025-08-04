package com.jsp.CourseEnrollmentSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jsp.CourseEnrollmentSystem.entity.Instructor;
import com.jsp.CourseEnrollmentSystem.entity.Student;
import com.jsp.CourseEnrollmentSystem.repository.InstructorRepository;
import com.jsp.CourseEnrollmentSystem.repository.StudentRepository;

@Repository
public class InstructorDao {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Save Instructor
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    // Get All Instructors
    public List<Instructor> getAllInstructorDetails() {
        return instructorRepository.findAll();
    }

    // Get Instructor By ID
    public Optional<Instructor> getInstructorById(int id) {
        return instructorRepository.findById(id);
    }
  //Update Instructor Details
  	public Optional<Instructor> updateByIdInstructor(int id){
  		return instructorRepository.findById(id);
  	}

    // Delete Instructor By ID
    public void deleteInstructorDetailsById(Instructor instructor) {
        instructorRepository.delete(instructor);
    }

    // Get Students By Instructor ID
    public List<Student> getStudentByInstructorId(int instructorId) {
        return studentRepository.findStudentsByInstructorId(instructorId);
    }

    // Get Page using Pagination And Sorting
    public Page<Instructor> findAll(Pageable pageable) {
        return instructorRepository.findAll(pageable);
    }
}
