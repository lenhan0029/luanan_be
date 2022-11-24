package com.luanan.quanlyghichu.Model.DTO.Request;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTO {

	@NotEmpty
    private String name;
	
	private String company;
	
    private String timeStart;
	
    private String timeEnd;
	
	private String description;
	
	@NotEmpty
	private String status;
	
	@NotEmpty
	private String priority;
	
	@NotEmpty
	private int id_account;
}
