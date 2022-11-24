package com.luanan.quanlyghichu.Model.DTO.Response;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

	private int id;
	@NotEmpty
    private String title;
	
    @NotEmpty
    private String location;
	
    @NotEmpty
    private OffsetDateTime time_start;
	
    private OffsetDateTime time_end;
	
	private String description;
}
