package com.sst.crud.service;

import java.util.List;

import com.sst.crud.entity.StudentDetailsTab;

//CRUD Operation
public interface StudentService {
	//Insert Student Details (C)
	public String registerStudents(StudentDetailsTab stdDetails);
	
	//Retrieve student details by student ID (R)
	public StudentDetailsTab getStudentdetailsById(Long stId);

	
	//Update Student Details by student ID (U)
	public StudentDetailsTab updateFullStudentDetailsById(StudentDetailsTab stdDetails);

	//Update Student Details by student ID (U)
	public StudentDetailsTab updateStudentDetailsById(Long stdId, String stdAddr, Long stdPhn);
	
	//Delete Student Details by student ID (D)
	public String deleteStudentdetailsById(Long stId);
	
	//Retrieve all student as per age range (R) Extra Method
	public List<StudentDetailsTab> getStudentListByAge(Integer stdAge);
}
