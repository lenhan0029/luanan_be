package com.luanan.quanlyghichu.Model.DTO.Response;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.luanan.quanlyghichu.Model.Entities.Subject;
import com.luanan.quanlyghichu.Model.Entities.TimeTable;
import com.luanan.quanlyghichu.Model.Entities.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

	private int id;
	private String code;
	private String name;
	private String classCode;
	private String day;
	private int start;
	private int number;
	private String room;
	private String note;
	private Type type;
	private int week;
}
