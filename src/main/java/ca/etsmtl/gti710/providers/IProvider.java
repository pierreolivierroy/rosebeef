package ca.etsmtl.gti710.providers;

import java.util.ArrayList;

import ca.etsmtl.gti710.models.Country;
import ca.etsmtl.gti710.models.Customer;
import ca.etsmtl.gti710.models.Order;
import ca.etsmtl.gti710.models.Product;

public interface IProvider {

	public ArrayList<Product> getProducts();
	public Product getProduct(int id);

	public ArrayList<Order> getOrders();
	public Order getOrder(int id);
    public int createOrder(Order order);
    public Order addLineOrder(int orderId, int productId, int quantity);

    public Country getCountry(int id);
    public ArrayList<Country> getCountries();

    public Customer getCustomer(int id);

}
