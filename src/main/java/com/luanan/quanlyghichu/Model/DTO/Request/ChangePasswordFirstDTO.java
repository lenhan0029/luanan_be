package com.luanan.quanlyghichu.Model.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordFirstDTO {

	private int accountid;
	private String newPassword;
}
