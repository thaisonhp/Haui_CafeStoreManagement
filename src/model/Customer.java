package model;

public class Customer {
	private String id;
    private String name;
    private String email;
    private String phonenumber;
    private String address;
    
    
	public Customer() {
		
	}


	public Customer(String id, String name, String email, String phonenumber, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.address = address;
	}


	public String getId() {
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


	public void setId(String id) {
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


	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", phonenumber=" + phonenumber
				+ ", address=" + address + "]";
	}
	
	
    
}
