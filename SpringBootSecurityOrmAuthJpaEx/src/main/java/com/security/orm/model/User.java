package com.security.orm.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
	
	
	@Id

	@GeneratedValue
	@Column(name="uid")
	private int uid;
	
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@ElementCollection
	@CollectionTable(name="role",joinColumns = @JoinColumn(name="uid"))
	
	@Column(name="role")
	private List<String> roles;
	

}
