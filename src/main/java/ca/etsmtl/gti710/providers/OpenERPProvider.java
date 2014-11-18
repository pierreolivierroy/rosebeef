package ca.etsmtl.gti710.providers;

import ca.etsmtl.gti710.models.*;
import ca.etsmtl.gti710.openErp.ClientAPI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;

public class OpenERPProvider implements IProvider {

	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:openERP-context.xml");
	ClientAPI client = (ClientAPI) context.getBean("client");

    public OpenERPProvider() {
        client.connect();
    }


    @Override
    public Customer getCustomer(int id) {

        HashMap<String, Object> customerInfo = client.readCustomer(id);
        if (customerInfo == null) {
            return null;
        }
        Customer customer = new Customer();

        if (!customerInfo.get("x_firstname").toString().equals("false")) {
            customer.setFirstname(customerInfo.get("x_firstname").toString());
        }
        if (!customerInfo.get("x_lastname").toString().equals("false")) {
            customer.setLastname(customerInfo.get("x_lastname").toString());
        }
        if (!customerInfo.get("street").toString().equals("false")) {
            customer.setAddress(customerInfo.get("street").toString());
        }
        if (!customerInfo.get("city").toString().equals("false")) {
            customer.setCity(customerInfo.get("city").toString());
        }
        if (!customerInfo.get("zip").toString().equals("false")) {
            customer.setZip(customerInfo.get("zip").toString());
        }
        if (!customerInfo.get("email").toString().equals("false")) {
            customer.setEmail(customerInfo.get("email").toString());
        }
        if (!customerInfo.get("phone").toString().equals("false")) {
            customer.setPhone(customerInfo.get("phone").toString());
        }
        if (!customerInfo.get("id").toString().equals("false")) {
            customer.setId((Integer)customerInfo.get("id"));
        }

        return customer;
    }

	@Override
	public Product getProduct(int id) {

	    HashMap<String, Object> openERPProduct = client.readProduct(id);

        if (openERPProduct == null) {
            return null;
        }
	    Product product = new Product(id);

        if (!openERPProduct.get("name").toString().equals("false")) {
            product.setName(openERPProduct.get("name").toString());
        }
        if (!openERPProduct.get("list_price").toString().equals("false")) {
            product.setPrice((Double) openERPProduct.get("list_price"));
        }
	    if (!openERPProduct.get("description").toString().equals("false")) {
	    	product.setDescription(openERPProduct.get("description").toString());
	    }

        if (!openERPProduct.get("qty_available").toString().equals("false")) {
            product.setQty_available((Double)openERPProduct.get("qty_available"));
        }

	    if (!openERPProduct.get("default_code").toString().equals("false")) {
	    	product.setCode(openERPProduct.get("default_code").toString());
	    }

        if (!openERPProduct.get("x_os").toString().equals("false")) {
            product.setOs(openERPProduct.get("x_os").toString());
        }

        if (!openERPProduct.get("x_camera").toString().equals("false")) {
            product.setCamera(openERPProduct.get("x_camera").toString());
        }

        if (!openERPProduct.get("x_display").toString().equals("false")) {
            product.setDisplay(openERPProduct.get("x_display").toString());
        }

        if (!openERPProduct.get("x_weight").toString().equals("false")) {
            product.setWeight(openERPProduct.get("x_weight").toString());
        }
        if (!openERPProduct.get("default_code").toString().equals("false")) {
            product.setCode(openERPProduct.get("default_code").toString());
        }

        ArrayList imagesList = new ArrayList();
        for (int i = 1; i <= 5;i++) {
            if (!openERPProduct.get("x_image"+i).toString().equals("false") && !openERPProduct.get("x_image"+i).toString().equals("")) {
                imagesList.add(openERPProduct.get("x_image"+i).toString());
            }
        }
        product.setImages(imagesList);

	    return product;
	}

	@Override
	public ArrayList<Product> getProducts() {

        Object[] idProducts = client.getProductList();
        ArrayList<Product> listProduits = new ArrayList<Product>();
        for (Object id : idProducts) {

            listProduits.add(getProduct((Integer)id));
        }
		return listProduits;
	}

    @Override
    public Order getOrder(int id) {

        HashMap<String, Object> orderInfo = client.readOrder(id);

        if (orderInfo == null) {
            return null;
        }
        Order order = new Order();

        if (!orderInfo.get("id").toString().equals("false")) {
            order.setOrder_id((Integer)orderInfo.get("id"));
        }
        if (!orderInfo.get("create_date").toString().equals("false")) {
            order.setOrder_date(orderInfo.get("create_date").toString());
        }
        if (!orderInfo.get("amount_untaxed").toString().equals("false")) {
            order.setAmount_no_taxes((Double) orderInfo.get("amount_untaxed"));
        }
        if (!orderInfo.get("amount_tax").toString().equals("false")) {
            order.setAmount_taxes((Double) orderInfo.get("amount_tax"));
        }
        if (!orderInfo.get("amount_total").toString().equals("false")) {
            order.setAmount_total((Double)orderInfo.get("amount_total"));
        }
        if (!orderInfo.get("state").toString().equals("false")) {
            order.setState(orderInfo.get("state").toString());
        }
        if (!orderInfo.get("partner_id").toString().equals("false")) {

            Object[] partner = (Object[])orderInfo.get("partner_id");
            order.setCustomer(getCustomer((Integer)partner[0]));
        }
        if (!orderInfo.get("order_line").toString().equals("false")) {

            Object[] lineOrderIds = (Object[]) orderInfo.get("order_line");
            ArrayList<SaleOrderLine> saleOrderLines = new ArrayList<SaleOrderLine>();

            for (Object lineOrderId : lineOrderIds) {

                HashMap<String, Object> lineOrderInfo = client.readLineOrder((Integer)lineOrderId);
                SaleOrderLine saleOrderLine = new SaleOrderLine();
                if (!lineOrderInfo.get("id").toString().equals("false")) {
                    saleOrderLine.setId((Integer)lineOrderInfo.get("id"));
                }
                if (!lineOrderInfo.get("name").toString().equals("false")) {
                    saleOrderLine.setName(lineOrderInfo.get("name").toString());
                }
                if (!lineOrderInfo.get("product_uom_qty").toString().equals("false")) {
                    saleOrderLine.setQuantity((Double)lineOrderInfo.get("product_uom_qty"));
                }
                if (!lineOrderInfo.get("price_unit").toString().equals("false")) {
                    saleOrderLine.setPrice((Double)lineOrderInfo.get("price_unit"));
                }
                if (!lineOrderInfo.get("price_subtotal").toString().equals("false")) {
                    saleOrderLine.setSubtotal((Double)lineOrderInfo.get("price_subtotal"));
                }
                saleOrderLines.add(saleOrderLine);
            }
            order.setSaleOrderLines(saleOrderLines);
        }
        return order;
    }

	@Override
	public ArrayList<Order> getOrders() {

        Object[] idOrders = client.getOrderList();
        ArrayList<Order> listOrders = new ArrayList<Order>();
        for (Object id : idOrders) {

            listOrders.add(getOrder((Integer)id));
        }
        return listOrders;
	}

    @Override
    public int createOrder(Order order) {

        Customer customer = order.getCustomer();
        int customerId = client.createCustomer(customer.getFirstname(), customer.getLastname(), customer.getAddress(), customer.getCity(), customer.getZip(), customer.getPhone(), customer.getEmail(),"52","36");
        int orderId = client.createOrder(customerId);
        ArrayList<SaleOrderLine> saleOrderLineArrayList = order.getSaleOrderLines();
        for (SaleOrderLine aSaleOrderLineArrayList : saleOrderLineArrayList) {

            client.createLineOrder(orderId, aSaleOrderLineArrayList.getQuantity().intValue(), aSaleOrderLineArrayList.getId());
        }
        return orderId;
    }

    @Override
    public Order addLineOrder(int orderId, int productId, int quantity) {
        client.createLineOrder(orderId, quantity , productId);
        return getOrder(orderId);
    }


    @Override
    public Country getCountry(int id) {

        HashMap<String, Object> openERPCountry = client.readCountry(id);

        if (openERPCountry == null) {
            return null;
        }
        Country country = new Country(id);
        if (!openERPCountry.get("name").toString().equals("false")) {
            country.setName(openERPCountry.get("name").toString());
        }
        if (!openERPCountry.get("code").toString().equals("false")) {
            country.setCode(openERPCountry.get("code").toString());
        }
        return country;
    }

    @Override
    public ArrayList<Country> getCountries() {
        Object[] idCountries = client.getCountryList();
        ArrayList<Country> listCountries = new ArrayList<Country>();
        for (Object id : idCountries) {

            listCountries.add(getCountry((Integer) id));
        }
        return listCountries;
    }

}
