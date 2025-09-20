package com.sst.crud.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sst.crud.entity.StudentDetailsTab;

public interface StudentRepo extends JpaRepository<StudentDetailsTab, Long> {
	
	// By using FindBy method, all students whose DOB is after the given cutoffDate
    List<StudentDetailsTab> findBystdDOBAfter(LocalDate cutoffDate);
	

}
