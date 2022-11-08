package org.bct.springboot.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="orders")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long orderId;
	
	@Column(name="order_name")
	private String orderName;
	
	@Column(name="start_date")
	private LocalDateTime startTime = LocalDateTime.now();
	 
	
	@Column(name="end_date")
	private LocalDateTime endTime = LocalDateTime.now();
	
	@Column(name="price")
	private double price;
	
	@OneToMany(mappedBy="order", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Item> items;
	
	
	@OneToOne(mappedBy="order", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Payment payments;
		
	//Default Constructor
	public Order() {
		super();
	}
	
	//Parameterized Constructor
	public Order(long orderId, String orderName, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.orderId = orderId;
		this.orderName = orderName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	//Getter and setter
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
}