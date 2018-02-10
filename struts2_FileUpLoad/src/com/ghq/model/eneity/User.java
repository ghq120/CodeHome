package com.ghq.model.eneity;

public class User {
	private int id;
	private String username;
	private String userpass;
	private String userphoto;
	
	public User(int id, String username, String userpass, String userphoto) {
		super();
		this.id = id;
		this.username = username;
		this.userpass = userpass;
		this.userphoto = userphoto;
	}
	
	public User( String username, String userpass, String userphoto) {
		this.username = username;
		this.userpass = userpass;
		this.userphoto = userphoto;
	}
	
	public User() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getUserphoto() {
		return userphoto;
	}

	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}
	
	
	
	
	
	
	
}
