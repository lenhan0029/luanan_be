package com.luanan.quanlyghichu.Model.Entities;

import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ownclass")
public class OwnClass {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "classcode")
    private String classcode;
	
	@Column(name = "code")
    @NotEmpty(message = "cannot generate name")
    private String code;
	
	@Column(name = "name")
    @NotEmpty(message = "cannot generate name")
    private String name;
	
	@Column(name = "progress")
    private String progress;
	
	@Column(name = "list_sv")
    private String listsv;
	
	@Column(name = "note")
    private String note;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_account")
	@JsonIgnore
	private Account account;
}
