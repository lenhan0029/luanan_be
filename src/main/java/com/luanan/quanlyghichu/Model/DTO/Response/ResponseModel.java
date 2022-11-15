package com.luanan.quanlyghichu.Model.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseModel {

	String message;
	int status;
	Object data;
	
	public ResponseModel(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	
}
