package ca.etsmtl.gti710.models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private Address billing_address;
	private Address order_address;
	private Address shipping_address;

    public Address getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(Address billing_address) {
        this.billing_address = billing_address;
    }

    public Address getOrder_address() {
        return order_address;
    }

    public void setOrder_address(Address order_address) {
        this.order_address = order_address;
    }

    public Address getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(Address shipping_address) {
        this.shipping_address = shipping_address;
    }
}
