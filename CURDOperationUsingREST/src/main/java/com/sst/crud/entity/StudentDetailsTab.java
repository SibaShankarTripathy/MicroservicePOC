package com.sst.crud.entity;

import java.io.Serializable;
import java.time.LocalDate;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="STUDENT_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailsTab implements Serializable{
	
	@Id
	@SequenceGenerator(name = "slNo",sequenceName = "STD_SEQ", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(generator = "slNo", strategy = GenerationType.SEQUENCE)
	@Column(name = "SL_NO")
	private Long stdId;
	
	@Column(name = "STD_ROLL_NO", length = 20)
	@NonNull
	private String stdRlNo;
	
	@Column(name = "STD_NAME", length = 20)
	@NonNull
	private String stdName;
	
	@Column(name = "STD_CLASS", length = 10)
	@NonNull
	private String stdClass;
	
	@Column(name = "STD_ADDRESS", length = 40)
	@NonNull
	private String stdAddress;
	
	@Column(name = "STD_DOB")
	@NonNull
	private LocalDate stdDOB;
	
	@Column(name = "STD_PHONE")
	@NonNull
	private Long stdPhone;
	
	@Column(name = "STD_FEES")
	@NonNull
	private Float stdFees;

}
