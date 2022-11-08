package org.bct.springboot.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long billNumber;
	
	@OneToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="order_id",nullable=false)
	private Order order;
	
	//Parameterized constructor
	public Payment() {
		super();
	}

	//Default constructor
	public Payment(long billNumber, Order order) {
		super();
		this.billNumber = billNumber;
		this.order = order;
	}

	//getter and setter
	public long getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(long billNumber) {
		this.billNumber = billNumber;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}