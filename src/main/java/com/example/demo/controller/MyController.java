package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;

@RestController
public class MyController {

	@Autowired
	StudentRepo studentRepo;


	//adding student in database
	@RequestMapping("/addstudent")
	public boolean addstudent(@RequestBody Student student)
	{
		try {
			studentRepo.save(student);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//delete students with the help of id
	@DeleteMapping(("/deleteStudent{id}"))
	public boolean deleteStudent(@PathVariable int id )
	{
		int counById = studentRepo.countById(id);
		if(counById>0)
		{
			try {
				studentRepo.deleteById(id);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}



	//updating students name in existing student
	@PutMapping("/updateStudent{id}and{name}")
	public boolean updateStudent(@PathVariable int id,@PathVariable String name)
	{

		int counById = studentRepo.countById(id);
		if(counById>0) {
			try 
			{
				//native query
				//				Student student = studentRepo.findById(id).get();
				//				studentRepo.updateNameInStudent(id, name);
				//				studentRepo.save(student);

				Student student = studentRepo.findById(id).get();
				student.setName(name);
				studentRepo.save(student);

				return true;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}


	//getting all students from database
	@GetMapping("/getAllstudent")  
	public List<Student> getAllStudents()   
	{  
		List<Student> findAll = studentRepo.findAll(); 
		return findAll;
	}  


	//get particular student with help of id
	@GetMapping("/getStudentById{id}")
	public Student student(@PathVariable int id)
	{
		int counById = studentRepo.countById(id);
		if(counById>0)
		{
			try {
				Student student =   studentRepo.findById(id).get(); 
				return  student;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}