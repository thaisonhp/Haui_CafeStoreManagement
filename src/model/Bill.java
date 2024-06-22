package model;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable{
    private String id;
    private Customer customer ; 
    private String date;
    private double total;
    private String createBy;

    
    // Constructor
    public Bill() {
		
	}


	public Bill(String id, Customer customer, String date, double total, String createBy) {
		super();
		this.id = id;
		this.customer = customer;
		this.date = date;
		this.total = total;
		this.createBy = createBy;
	}


	public String getId() {
		return id;
	}


	public Customer getCustomer() {
		return customer;
	}


	public String getDate() {
		return date;
	}


	public double getTotal() {
		return total;
	}


	public String getCreateBy() {
		return createBy;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}


	@Override
	public String toString() {
		return "Bill [id=" + id + ", customer=" + customer + ", date=" + date + ", total=" + total + ", createBy="
				+ createBy + "]";
	}
    
}

