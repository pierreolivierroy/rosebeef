package ca.etsmtl.gti710.models;

import java.util.Date;

public class Order {
	
	private int order_id;
	private String store;
	private Date order_date;
	private Customer customer;
	
	private double amount_no_taxes;
	private double amount_taxes;
	private double amount_total;
	
	private String state;
}
