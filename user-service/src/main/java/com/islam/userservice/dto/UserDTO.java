package com.islam.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty
	@Size(min = 2, max = 50)
	@Email(regexp = ".+[@].+[\\.].+")
	private String email;
	@NotEmpty
	@Size(min = 2, max = 50)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@NotEmpty
	@Size(min = 2, max = 50)
	private String firstName;
	@NotEmpty
	@Size(min = 2, max = 50)
	private String lastName;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Short enabled;

	private List<Integer> rolesIds;

	private String authToken;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public List<Integer> getRolesIds() {
		return rolesIds;
	}

	public void setRolesIds(List<Integer> rolesIds) {
		this.rolesIds = rolesIds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

}
