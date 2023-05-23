package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entity.Student;
import jakarta.transaction.Transactional;

public interface StudentRepo extends JpaRepository<Student, Integer> 
{

	

    @Modifying
    @Transactional
	@Query(value="update student set name=?1, where id=?2",nativeQuery = true)
	void updateNameInStudent(int id,String name);	
    
    
    
    int countById(int id);
//    int countByUsername(String username);
//	User findByUsername(String username);
}
