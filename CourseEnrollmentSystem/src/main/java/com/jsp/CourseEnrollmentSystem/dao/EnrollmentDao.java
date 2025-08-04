package com.jsp.CourseEnrollmentSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jsp.CourseEnrollmentSystem.entity.Enrollment;
import com.jsp.CourseEnrollmentSystem.repository.EnrollmentRepository;

@Repository
public class EnrollmentDao {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Save Enrollment Details
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // Get All Enrollment Information
    public List<Enrollment> getAllEnrollmentDetails() {
        return enrollmentRepository.findAll();
    }

    // Get Enrollment By Id
    public Optional<Enrollment> getEnrollmentById(int id) {
        return enrollmentRepository.findById(id);
    }

    // Update Enrollment Details
    public Optional<Enrollment> updateByIdEnrollment(int id) {
        return enrollmentRepository.findById(id);
    }

    // Delete Enrollment
    public void deleteEnrollmentDetailsById(Enrollment enrollment) {
        enrollmentRepository.delete(enrollment);
    }

    // Get Enrollment by Student Id
    public List<Enrollment> getEnrollmentByStudentId(int studentId) {
        return enrollmentRepository.findEnrollmentByStudentId(studentId);
    }

    // Get Enrollment by Course Id
    public List<Enrollment> getEnrollmentByCourseId(int courseId) {
        return enrollmentRepository.findEnrollmentByCourseId(courseId);
    }

    // Get Enrollment with Pagination and Sorting
    public Page<Enrollment> getEnrollment(Pageable pageable) {
        return enrollmentRepository.findAll(pageable);
    }
}
