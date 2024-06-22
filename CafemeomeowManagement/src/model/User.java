package model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	private String phonenumber;
	private String address;
	private String password;
	private boolean status;

	private static int id_count = 1;

	public User() {

	}

	public User(String name, String email, String phonenumber, String address, String password, boolean status) {
		this.id = User.id_count;
		User.id_count++;
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.address = address;
		this.password = password;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phonenumber=" + phonenumber + ", address="
				+ address + ", password=" + password + ", status=" + status + "]";
	}

}
