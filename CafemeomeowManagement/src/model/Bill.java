package model;

import java.util.Date;

public class Bill {
    private int id;
    private String name;
    private String phonenumber;
    private String email;
    private Date date;
    private double total;
    private String createBy;

    
    // Constructor
    public Bill() {
		
	}
    
    public Bill(int id, String name, String phonenumber, String email, Date date, double total, String createBy) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.date = date;
        this.total = total;
        this.createBy = createBy;
    }

    

	// Getter và Setter cho các thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                ", total=" + total +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}

