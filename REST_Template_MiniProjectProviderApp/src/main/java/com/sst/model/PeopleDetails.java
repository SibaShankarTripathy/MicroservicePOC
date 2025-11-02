package com.sst.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDetails {
	private String pplName;
	private String ppladdress;
	private Integer pplAge;
	private Long pplPhone;
}
