package com.luanan.quanlyghichu.Model.Entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import ch.qos.logback.core.subst.Token.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "timetable")
public class TimeTable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "username")
    private String username;
	
	@Column(name = "password")
    private String password;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_account")
	private Account account;
	
	@OneToMany(mappedBy = "timetable", fetch = FetchType.LAZY)
	private Set<Subject> subjects;
	
}
