package ca.etsmtl.gti710.providers;

import java.util.ArrayList;
import java.util.HashMap;

import ca.etsmtl.gti710.models.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.etsmtl.gti710.openErp.ClientAPI;

public class OpenERPProvider implements IProvider {

	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:OpenERP-context.xml");
	ClientAPI client = (ClientAPI) context.getBean("client");

    public OpenERPProvider() {
        client.connect();
    }

	@Override
	public Product getProduct(int id) {

	    HashMap<String, Object> openERPProduct = client.readProduct(id);

	    Product product = new Product(id);
	    product.setName(openERPProduct.get("name").toString());
	    product.setPrice((Double) openERPProduct.get("lst_price"));

	    if (!openERPProduct.get("description").toString().equals("false")) {
	    	product.setDescription(openERPProduct.get("description").toString());
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
            product.getDisplay(openERPProduct.get("x_display").toString());
        }

        if (!openERPProduct.get("weight").toString().equals("false")) {
            product.setWeight(openERPProduct.get("weight").toString());
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
        Order order = new Order();
        order.setOrder_id((Integer)orderInfo.get("id"));
        order.setOrder_date(orderInfo.get("date_order").toString());

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
        Object[] lineOrderIds = (Object[]) orderInfo.get("order_line");
        ArrayList<SaleOrderLine> saleOrderLines = new ArrayList<SaleOrderLine>();

        for (Object lineOrderId : lineOrderIds) {

            HashMap<String, Object> lineOrderInfo = client.readLineOrder((Integer)lineOrderId);
            SaleOrderLine saleOrderLine = new SaleOrderLine();
            saleOrderLine.setName(lineOrderInfo.get("name").toString());
            saleOrderLine.setQuantity((Double)lineOrderInfo.get("product_uom_qty"));
            saleOrderLine.setPrice((Double)lineOrderInfo.get("price_unit"));
            saleOrderLine.setSubtotal((Double)lineOrderInfo.get("price_subtotal"));
            saleOrderLines.add(saleOrderLine);
        }

        order.setSaleOrderLines(saleOrderLines);
        Customer customer = new Customer();
        Address adress = new Address();
        adress.setCity("Montreal");
        customer.setBilling_address(adress);
        order.setCustomer(customer);
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
    public Order createOrder(Order order) {

//        int orderId = client.createOrder("name", customerId);
        return order;
    }

    @Override
    public Order addLineOrder(int orderId, int productId, int quantity) {
        client.createLineOrder(orderId, quantity , productId);
        return getOrder(orderId);
    }


    @Override
    public Country getCountry(int id) {

        HashMap<String, Object> openERPCountry = client.readCountry(id);

        Country country = new Country(id);
        country.setName(openERPCountry.get("name").toString());
        country.setCode(openERPCountry.get("code").toString());

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
