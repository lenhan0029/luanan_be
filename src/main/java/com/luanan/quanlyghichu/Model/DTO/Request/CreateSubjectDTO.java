package com.luanan.quanlyghichu.Model.DTO.Request;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubjectDTO {
	
    private String day;
	
    private Integer start;
	
    private String room;
	
    private String note;
    
    private String type;
    
    private int reference;
    
    private int id_account;
    
    private int week;
}
