package com.luanan.quanlyghichu.Model.DTO.Request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

	@NotEmpty
	private String name;
	
	@NotEmpty
	private int id_account;
}
