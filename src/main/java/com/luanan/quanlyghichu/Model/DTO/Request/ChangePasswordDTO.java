package com.luanan.quanlyghichu.Model.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {

	private int accountid;
	private String oldPassword;
	private String newPassword;
}
