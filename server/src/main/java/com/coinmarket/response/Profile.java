package com.coinmarket.response;

public class Profile {
	private String id;
	private String email;
	private String name;
	
	public Profile() {
		
	}
	
	public Profile(String id, String email, String name) {
		this.id = id;
		this.email = email;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
