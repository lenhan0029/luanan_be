package com.luanan.quanlyghichu.Model.Entities;

import java.sql.Timestamp;
import java.util.Set;

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
import javax.persistence.OneToMany;
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
@Table(name = "project")
public class Project {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "name")
    @NotEmpty(message = "cannot generate name")
    private String name;
	
	@Column(name = "time_start")
    @NotEmpty(message = "cannot generate time start")
    private Timestamp timeStart;
	
	@Column(name = "time_end")
    private Timestamp timeEnd;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
    private Status status;
	
	@Column(name = "priority")
	@Enumerated(EnumType.STRING)
    private Priority priority;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_account")
	private Account account;
	
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Task> tasks;
	
}
