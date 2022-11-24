package com.luanan.quanlyghichu.Model.DTO.Request;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDTO {

    @NotEmpty
    private String title;
	
    @NotEmpty
    private String location;
	
    private String timeStart;
	
    private String timeEnd;
	
	private String description;
	
	@NotEmpty
	private int id_account;
}
