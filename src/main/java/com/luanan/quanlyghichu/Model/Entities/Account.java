package com.luanan.quanlyghichu.Model.Entities;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "account")
public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    @NotEmpty(message = "cannot generate username")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "cannot generate password")
    private String password;

    @Column(name = "status")
    private boolean status;
    
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Note> notes;
	
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Project> projects;
    
    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private TimeTable timetables;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Category> categories;
}
