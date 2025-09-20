package com.sst.crud.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sst.crud.entity.StudentDetailsTab;
import com.sst.crud.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	public StudentRepo studentRepo;

	@Override
	public String registerStudents(StudentDetailsTab stdDetails) {
		String saveStatus = null;
		try {
			Long stdId = studentRepo.save(stdDetails).getStdId();
			saveStatus = "Student Register Success fully, ID: " + stdId;
		} catch (Exception e) {
			System.out.println("Exception occured while inserting student record....");
		}
		return saveStatus;
		
	}

	@Override
	public StudentDetailsTab getStudentdetailsById(Long stId) {
		StudentDetailsTab studentDetails = null;
		try {
			studentDetails = studentRepo.findById(stId).orElse(null);
		} catch (Exception e) {
			System.out.println("Exception occured while retrieving Stdent Details");
		}
		return studentDetails;
	}
	
	@Override
	public List<StudentDetailsTab> getStudentListByAge(Integer stdAge) {
		List<StudentDetailsTab> bystdAgeAfter = null;
		try {
			LocalDate birthDate = LocalDate.now().minusYears(stdAge);
			bystdAgeAfter = studentRepo.findBystdDOBAfter(birthDate);
		} catch (Exception e) {
			System.out.println("Exception occured while getting student list based on age....");
		}
		return bystdAgeAfter;
	}

	@Override
	public StudentDetailsTab updateFullStudentDetailsById(StudentDetailsTab stdDetails) {
		StudentDetailsTab studentDetails = null;
		try {
			boolean existsById = studentRepo.existsById(stdDetails.getStdId());
			if (existsById) {
				studentRepo.save(stdDetails);
				studentDetails = studentRepo.findById(stdDetails.getStdId()).orElse(null);
			}	
		} catch (Exception e) {
			System.out.println("Exception occured while updating full details of student....");
		}
		
		return studentDetails;
	}

	@Override
	public StudentDetailsTab updateStudentDetailsById(Long stdId, String stdAddr, Long stdPhn) {
		StudentDetailsTab stdDetails = null;
		try {
			//Check student available or not
			boolean studentExistsById = studentRepo.existsById(stdId);
			if(studentExistsById) {
				stdDetails = studentRepo.findById(stdId).orElse(null);
				stdDetails.setStdAddress(stdAddr);
				stdDetails.setStdPhone(stdPhn);
				//Update student details
				studentRepo.save(stdDetails);
			} else {
				System.out.println("No Record Found on this ID: "+stdId);
			}
			
		} catch (Exception e) {
			System.out.println("Exception occured while updating record having id: "+stdId);
			return stdDetails;
		}
		return stdDetails;
		
	}

	
	@Override
	public String deleteStudentdetailsById(Long stdId) {
		String deleteStatus = null;
		try {
			boolean stdExist = studentRepo.existsById(stdId);
			if(stdExist) {
				studentRepo.deleteById(stdId);
				deleteStatus = "Student details deleted successfully....";
			} else {
				deleteStatus = "No record found on this student Id: "+stdId;
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while deleting student record....");
		}
		return deleteStatus;
	}
	
}
