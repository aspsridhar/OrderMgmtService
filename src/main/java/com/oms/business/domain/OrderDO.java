package com.oms.business.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the ORDER database table.
 * 
 */
@Entity
@Table(name="ORDER")
public class OrderDO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="ORDER_ID_SEQ", sequenceName="ORDER_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDER_ID_SEQ")
	@Column(name="ORDER_ID", unique = true, nullable = false)
	private long orderId;
	
	@Column(name="QUANTITY")
	private int noOfBricks;
	
	@Column(name="PRICE")
	private double price;
	
	@Column(name="CATEGORY")
	private String category;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="COMPLETE")
	private boolean completed;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ORDER_REF_ID")
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
	

	public int getNoOfBricks() {
		return noOfBricks;
	}

	public void setNoOfBricks(int noOfBricks) {
		this.noOfBricks = noOfBricks;
	}

	public OrderDO(long orderId,double price,int noOfBricks, String category, String status, boolean completed, String description,
			long orderReferanceID) {
		super();
		this.orderId = orderId;
		this.noOfBricks=noOfBricks;
		this.price = price;
		this.category = category;
		this.status = status;
		this.completed = completed;
		this.description = description;
		this.orderReferanceID = orderReferanceID;
	}

	public OrderDO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	

}
