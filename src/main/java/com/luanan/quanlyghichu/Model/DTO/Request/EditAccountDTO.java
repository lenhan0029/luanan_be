package com.luanan.quanlyghichu.Model.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditAccountDTO {

	private int accountid;
	private int status;
	private String role;
}
