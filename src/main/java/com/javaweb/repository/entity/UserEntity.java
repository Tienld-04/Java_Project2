package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
	@Id // đánh dấu khóa chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) // tự  động tăng dần
	private Long id;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "pasword")
	private String pasword;
	
	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "email")
	private String email;

//	thủ công
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//	private List<UserRoleEntity> userRoleEntities = new ArrayList<>();
//	
	// tự động
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
						joinColumns = @JoinColumn(name = "userid", nullable = false),
						inverseJoinColumns = @JoinColumn(name = "roleid", nullable = false))
	private List<RoleEntity> roles = new ArrayList<>();
	
	
	
	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	
	
}
