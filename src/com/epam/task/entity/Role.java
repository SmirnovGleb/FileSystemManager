package com.epam.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Role. describes the user role.
 */
@Entity
@Table(name = "role")
public class Role extends AbstractEntity {

	/** The Constant ROLE_ADMIN. */
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	/** The Constant ROLE_USER. */
	public static final String ROLE_USER = "ROLE_USER";

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "r_id")
	private int id;

	/** The role. */
	@Column(name = "r_role")
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}

}
