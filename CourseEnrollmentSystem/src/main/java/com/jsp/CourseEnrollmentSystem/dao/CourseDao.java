package com.jsp.CourseEnrollmentSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jsp.CourseEnrollmentSystem.entity.Course;
import com.jsp.CourseEnrollmentSystem.repository.CourseRepository;

@Repository
public class CourseDao {

    @Autowired
    private CourseRepository courseRepository;

    // Save Course records
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // Get All The course Information
    public List<Course> getAllCourseDetails() {
        return courseRepository.findAll();
    }

    // Get Course By Id
    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    // Update Course (same as getCourseById, redundant but included)
    public Optional<Course> getByIdCourse(int id) {
        return courseRepository.findById(id);
    }

    // Delete course
    public void deleteCourseDetailsById(Course course) {
        courseRepository.delete(course);
    }

    // Get Course by Instructor Id
    public List<Course> getCourseByInstructorId(int instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    // Get course With Pagination And Sorting
    public Page<Course> getAllCourse(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
}
