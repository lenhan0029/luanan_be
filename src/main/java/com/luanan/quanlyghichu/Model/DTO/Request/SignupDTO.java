package com.luanan.quanlyghichu.Model.DTO.Request;

import javax.validation.constraints.NotEmpty;

import com.luanan.quanlyghichu.Model.Entities.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private Integer status;
	@NotEmpty
	private String role;
}
