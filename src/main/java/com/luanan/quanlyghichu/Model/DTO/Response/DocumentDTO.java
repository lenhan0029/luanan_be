package com.luanan.quanlyghichu.Model.DTO.Response;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDTO {
	
	@NotEmpty
	private int id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String link;
	
	@NotEmpty
	private String category;
}
