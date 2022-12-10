package com.luanan.quanlyghichu.Model.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "subject")
public class Subject {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "code")
    private String code;
	
	@Column(name = "name")
    @NotEmpty(message = "cannot generate name")
    private String name;
	
	@Column(name = "subject_group")
    @NotEmpty(message = "cannot generate subject group")
    private String subjectGroup;
	
	@Column(name = "stc")
    private Integer stc;
	
	@Column(name = "class_code")
    private String classCode;
	
	@Column(name = "practise")
    private boolean practise;
	
	@Column(name = "day")
    private String day;
	
	@Column(name = "start")
    private Integer start;
	
	@Column(name = "number")
    private Integer number;
	
	@Column(name = "room")
    private String room;
	
	@Column(name = "list_student")
    private String listStudent;
	
	@Column(name = "note")
    private String note;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@OneToOne
	@JoinColumn(name = "subject_reference", referencedColumnName = "id")
	private Subject subjectReferenceFrom;
	
	@OneToOne(mappedBy = "subjectReferenceFrom")
	@JsonIgnore
	private Subject subjectReferenceTo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_timetable")
	@JsonIgnore
	private TimeTable timetable;
}
