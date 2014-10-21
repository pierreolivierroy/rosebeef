package ca.etsmtl.gti710.models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private Address billing_address;
	private Address order_address;
	private Address shipping_address;
	private List<Order> orders = new ArrayList<Order>();
}
