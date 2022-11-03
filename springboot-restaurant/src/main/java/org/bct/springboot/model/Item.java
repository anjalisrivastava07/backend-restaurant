package org.bct.springboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="items")
public class Item implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long itemId;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="item_price")
	private Double itemPrice;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="order_id",nullable=false)
	private Order order;

	//parameterized constructor
	public Item(long itemId, String itemName, Double itemPrice, Order order) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.order = order;
	}

	//default constructor
	public Item() {
		super();
	}

	//getter and setter
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}