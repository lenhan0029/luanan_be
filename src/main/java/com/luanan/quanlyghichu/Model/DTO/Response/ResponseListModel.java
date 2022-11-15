package com.luanan.quanlyghichu.Model.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseListModel {

	String message;
	int status;
	Object[] data;
	
}