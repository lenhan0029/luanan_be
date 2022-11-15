package com.luanan.quanlyghichu.Model.Entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "note")
public class Note {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "title")
    @NotEmpty(message = "cannot generate title")
    private String title;
	
	@Column(name = "location")
    @NotEmpty(message = "cannot generate location")
    private String location;
	
	@Column(name = "time_start")
    @NotEmpty(message = "cannot generate time start")
    private Timestamp timeStart;
	
	@Column(name = "time_end")
    private Timestamp timeEnd;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_account")
	private Account account;
}
