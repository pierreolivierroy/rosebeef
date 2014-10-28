package ca.etsmtl.gti710.models;

import java.util.ArrayList;

public class Order {
	
	private int order_id;
	private String order_date;
	private Customer customer;

	private double amount_no_taxes;
	private double amount_taxes;
	private double amount_total;

	private String state;

    private ArrayList<SaleOrderLine> saleOrderLines;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount_no_taxes() {
        return amount_no_taxes;
    }

    public void setAmount_no_taxes(double amount_no_taxes) {
        this.amount_no_taxes = amount_no_taxes;
    }

    public double getAmount_taxes() {
        return amount_taxes;
    }

    public void setAmount_taxes(double amount_taxes) {
        this.amount_taxes = amount_taxes;
    }

    public double getAmount_total() {
        return amount_total;
    }

    public void setAmount_total(double amount_total) {
        this.amount_total = amount_total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<SaleOrderLine> getSaleOrderLines() {
        return saleOrderLines;
    }

    public void setSaleOrderLines(ArrayList<SaleOrderLine> saleOrderLines) {
        this.saleOrderLines = saleOrderLines;
    }
}
