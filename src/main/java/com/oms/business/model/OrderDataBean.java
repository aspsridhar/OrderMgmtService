package com.oms.business.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The persistent class for the ORDER database table.
 * 
 */
@Entity
public class OrderDataBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long orderId;
	
	private double price;
	
	private String category;
	
	private String status;
	
	private boolean completed;
	
	private String description;
	
	private long orderReferanceID;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getOrderReferanceID() {
		return orderReferanceID;
	}

	public void setOrderReferanceID(long orderReferanceID) {
		this.orderReferanceID = orderReferanceID;
	}

	public OrderDataBean(long orderId, double price, String category, String status, boolean completed, String description,
			long orderReferanceID) {
		super();
		this.orderId = orderId;
		this.price = price;
		this.category = category;
		this.status = status;
		this.completed = completed;
		this.description = description;
		this.orderReferanceID = orderReferanceID;
	}
	
	
	
	
	
	
	

}
