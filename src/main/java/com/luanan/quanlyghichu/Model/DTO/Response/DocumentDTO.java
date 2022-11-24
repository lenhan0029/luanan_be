package com.luanan.quanlyghichu.Model.DTO.Response;

import javax.validation.constraints.NotEmpty;

import com.luanan.quanlyghichu.Model.Entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
	
	@NotEmpty
	private int id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String link;
	
	private int id_category;
	@NotEmpty
	private String category;
}
