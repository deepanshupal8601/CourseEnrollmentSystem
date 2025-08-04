package com.jsp.CourseEnrollmentSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CourseEnrollmentSystem.dao.InstructorDao;
import com.jsp.CourseEnrollmentSystem.dto.ResponseStructure;
import com.jsp.CourseEnrollmentSystem.entity.Instructor;
import com.jsp.CourseEnrollmentSystem.entity.Student;
import com.jsp.CourseEnrollmentSystem.exception.IdNotFoundException;

@Service
public class InstructorService {
	@Autowired
	private InstructorDao instructorDao;
	public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(Instructor instructor){
		Instructor saved = instructorDao.saveInstructor(instructor);
		ResponseStructure<Instructor> structure = new ResponseStructure<Instructor>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Instructor Record Saved Succesfully");
		structure.setData(saved);
		return new ResponseEntity<ResponseStructure<Instructor>>(structure,HttpStatus.OK);
		
	}
	
	//Get All Instructor Records
	public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructorDetails(){
		List<Instructor> instructors= instructorDao.getAllInstructorDetails();
		ResponseStructure<List<Instructor>> structure = new ResponseStructure<List<Instructor>>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Instructor Details Successfully");
		structure.setData(instructors);
		return new ResponseEntity<ResponseStructure<List<Instructor>>>(structure ,HttpStatus.OK);
		
		
	}
	//Get Instructor By Id
	public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(int id){
		Optional<Instructor>optInstrutor = instructorDao.getInstructorById(id);
		ResponseStructure<Instructor>structure = new ResponseStructure<Instructor>();
		if(optInstrutor.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Instructor Details Access By Id Successfull");
			structure.setData(optInstrutor.get());
			return new ResponseEntity<ResponseStructure<Instructor>>(structure,HttpStatus.OK);
			
		}
		else {
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Student Id Not Exist");
		structure.setData(null);
		return new ResponseEntity<ResponseStructure<Instructor>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	// Update Instructor
	public ResponseEntity<ResponseStructure<Instructor>> updateByIdInstructor(Instructor instructor, int id){
		Optional<Instructor>optInstructor =instructorDao.updateByIdInstructor(id);
		ResponseStructure<Instructor>structure1 = new ResponseStructure<Instructor>();
		if(optInstructor.isPresent()) {
			instructor.setId(id);
			Instructor updateInstrcutor= instructorDao.saveInstructor(instructor);
			structure1.setStatusCode(HttpStatus.OK.value());
			structure1.setMessage("Update Succesfully");
			structure1.setData(updateInstrcutor);
			return new ResponseEntity<>(structure1,HttpStatus.OK);
		}
		else {
			structure1.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure1.setMessage("Instructor not found with ID:"+id);
			structure1.setData(null);
			return new ResponseEntity<>(structure1,HttpStatus.NOT_FOUND);
		}
	}
	
//delete Instructor By Id
	public ResponseEntity<ResponseStructure<String>>deleteInstructorDetailsById(int id){
		
		Optional<Instructor>optInstrutor= instructorDao.getInstructorById(id);
		if(optInstrutor.isPresent()) {
			instructorDao.deleteInstructorDetailsById(optInstrutor.get());
			ResponseStructure<String>structure= new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Instructor deleted successfully");
			structure.setData("Deleted Instructor with ID:"+id);
			return new ResponseEntity<>(structure, HttpStatus.OK);
			
		}
		else {
			throw new IdNotFoundException();
		}
	}
	
		// Get Student by Instructor Id
		public ResponseEntity<ResponseStructure<List<Student>>>getStudentsByInstructorId(int instructorId){
			List<Student>students= instructorDao.getStudentByInstructorId(instructorId);
			if(students.isEmpty()) {
				throw new IdNotFoundException();
			}
			else {
				ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("Student Fetched by Instructor ID");
				structure.setData(students);
				return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.OK);
			}
		}
		//  By Using Pagination & Sorting
		public ResponseEntity<ResponseStructure<Page<Instructor>>>getInstructorWithPaginationAndSorting(int page, int size
				, String sortBy, String direction){
			Sort sort = direction.equalsIgnoreCase("ase")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
			
			Pageable pageable = PageRequest.of(page, size,sort);
			Page<Instructor>pageResult = instructorDao.findAll(pageable);
			ResponseStructure<List<Instructor>> structure= new ResponseStructure<List<Instructor>>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Instructor Fetched With Pagination and Sorting");
			structure.setData(pageResult.getContent());
			return new ResponseEntity<ResponseStructure<Page<Instructor>>>(HttpStatus.OK);
			
					
	  }
	}


