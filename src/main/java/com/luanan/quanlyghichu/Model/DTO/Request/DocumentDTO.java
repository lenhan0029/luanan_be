package com.luanan.quanlyghichu.Model.DTO.Request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDTO {

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String link;
	
	@NotEmpty
	private int id_category;
}
