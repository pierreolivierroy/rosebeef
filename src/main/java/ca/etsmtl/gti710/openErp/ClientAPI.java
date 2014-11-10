package ca.etsmtl.gti710.openErp;


import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

public class ClientAPI {

    private String host = null;
    private int port = 0;
    private String login = null;
    private String password = null;
    private String database = null;
    private int userId;

    public ClientAPI() {

    }

    public void connect() {

        Object[] params;
        XmlRpcClient xmlrpcLogin = new XmlRpcClient();

        XmlRpcClientConfigImpl xmlrpcConfigLogin = new XmlRpcClientConfigImpl();
        xmlrpcConfigLogin.setEnabledForExtensions(true);
        try {
            xmlrpcConfigLogin.setServerURL(new URL("http", host, port, "/xmlrpc/common"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        xmlrpcLogin.setConfig(xmlrpcConfigLogin);

        try {

            params = new Object[] {database, login, password};
            Object id = xmlrpcLogin.execute("login", params);
            if (id instanceof Integer) {
                userId = (Integer)id;
            }
        }
        catch (XmlRpcException e) {

            System.out.println(e);
        }
        catch (Exception e)
        {

            System.out.println(e);
        }
    }

    public HashMap<String, Object> readProduct(int product_id) {

        Object[] fields = new Object[15];
        fields[0] = "id";
        fields[1] = "name";
        fields[2] = "description";
        fields[3] = "default_code";
        fields[5] = "lst_price";
        fields[6] = "x_image1";
        fields[7] = "x_image2";
        fields[8] = "x_image3";
        fields[9] = "x_image4";
        fields[10] = "x_image5";
        fields[11] = "x_os";
        fields[12] = "x_camera";
        fields[13] = "x_display";
        fields[14] = "weight";

        return read("product.product", product_id, fields);
    }

    public HashMap<String, Object> readOrder(int order_id) {

        return read("sale.order", order_id, null);
    }

    public HashMap<String, Object> readLineOrder(Integer lineOrderId) {

        return read("sale.order.line", lineOrderId, null);
    }

    public int createOrder(String name, int customer_id){

        HashMap<String, Object> orderInfo = new HashMap<String, Object>();
        orderInfo.put("date_order", new Date().toString());
        orderInfo.put("shop_id", 1);
        orderInfo.put("picking_policy", "direct");
        orderInfo.put("order_policy", "manual");
        orderInfo.put("invoice_quantity", "order");
        orderInfo.put("pricelist_id", 1);

        orderInfo.put("partner_id", customer_id);
        orderInfo.put("partner_shipping_id", customer_id);
        orderInfo.put("partner_invoice_id", customer_id);
        orderInfo.put("partner_order_id", customer_id);

        Object id = create("sale.order", orderInfo);
        return (Integer) id;
    }

    public int createLineOrder(int orderId, int quantity, int productId){

//        HashMap<String, Object> product = readProduct(productId);

        HashMap<String, Object> lineInfo = new HashMap<String, Object>();
        lineInfo.put("order_id", 5);
//        lineInfo.put("product_uom_qty", 1);
        lineInfo.put("product_uos_qty",1);
        lineInfo.put("product_uom", 1);
        lineInfo.put("product_id", 7);
        lineInfo.put("state", "confirmed");
        lineInfo.put("name", "Test");
        lineInfo.put("price_unit", 10);
//        lineInfo.put("order_partner_id", 10);

        Object id = create("sale.order.line", lineInfo);
        return (Integer)id;
    }

    public HashMap<String,Object> readCountry(int id) {

        return read("res.country", id, null);
    }

    public int createProduct(String name, String description, String[] imageArray, String os, String camera, String display, String weight){

        HashMap<String, Object> productInfo = new HashMap<String, Object>();
        productInfo.put("name", name);
        productInfo.put("type", "product");
        productInfo.put("procure_method", "make_to_stock");
        productInfo.put("supply_method", "buy");
        productInfo.put("cost_method", "standard");
        productInfo.put("categ_id", 1);
        productInfo.put("uom_id", 1);
        productInfo.put("uom_po_id", 1);
        productInfo.put("valuation", "manual_periodic");
        productInfo.put("description", description);
        productInfo.put("x_image1", imageArray[0]);
        productInfo.put("x_image2", imageArray[1]);
        productInfo.put("x_image3", imageArray[2]);
        productInfo.put("x_image4", imageArray[3]);
        productInfo.put("x_image5", imageArray[4]);
        productInfo.put("x_os", os);
        productInfo.put("x_camera", camera);
        productInfo.put("x_display", display);
        productInfo.put("weight", weight);

        Object id = create("product.product", productInfo);
        return (Integer) id;
    }


    public void createCustomer(String firstName, String lastName, String address, String city, String postalCode, String phone, String mobilePhone, String email, String province_id, String country_id) {

        HashMap<String, Object> partnerInfo = new HashMap<String, Object>();
        partnerInfo.put("name", firstName + " " + lastName);
        partnerInfo.put("lang", "fr_FR");
        partnerInfo.put("customer", true);

        Object id = create("res.partner", partnerInfo);

        HashMap<String, Object> addressInfo = new HashMap<String, Object>();
        partnerInfo.put("name", firstName + " " + lastName);
        addressInfo.put("partner_id", id);
        addressInfo.put("street", address);
        addressInfo.put("zip", postalCode);
        addressInfo.put("city", city);
        addressInfo.put("phone", phone);
        addressInfo.put("mobile", mobilePhone);
        addressInfo.put("email", email);
        addressInfo.put("country_id", country_id);
        addressInfo.put("state_id", province_id);

        create("res.partner.address", addressInfo);
    }

    public Object[] getProductList() {

        Object searchParam[] = new Object[3];
        searchParam[0] = "name";
        searchParam[1] = "like";
        searchParam[2] = "";
        Vector<Object> param = new Vector<Object>();
        param.add(searchParam);
        return search("product.product", param);
    }

    public Object[] getOrderList() {

        Object searchParam[] = new Object[3];
        searchParam[0] = "name";
        searchParam[1] = "like";
        searchParam[2] = "";
        Vector<Object> param = new Vector<Object>();
        param.add(searchParam);
        return search("sale.order", param);
    }

    public Object[] getCountryList() {

        Object searchParam[] = new Object[3];
        searchParam[0] = "name";
        searchParam[1] = "like";
        searchParam[2] = "";
        Vector<Object> param = new Vector<Object>();
        param.add(searchParam);
        return search("res.country", param);
    }

    private Object create(String table, HashMap<String, Object> info) {

        XmlRpcClient xmlrpcLogin = getXmlrpcLogin();

        try {
            Object create[]=new Object[7];
            create[0] = database;
            create[1] = userId;
            create[2] = password;
            create[3] = table;
            create[4] = "create";
            create[5] = info;

            Object id = xmlrpcLogin.execute("execute", create);
            return id;
        }
        catch (XmlRpcException e) {

            //logger.warn("XmlException Error while logging to OpenERP: ",e);
            System.out.println(e);
        }
        catch (Exception e){

            //logger.warn("Error while logging to OpenERP: ",e);
            System.out.println(e);
        }
        return null;

    }

    private Object write(String table, int id, HashMap<String, Object> info) {

        XmlRpcClient xmlrpcLogin = getXmlrpcLogin();

        try {
            Object write[]=new Object[7];
            write[0] = database;
            write[1] = userId;
            write[2] = password;
            write[3] = table;
            write[4] = "write";
            write[5] = id;
            write[6] = info;

            HashMap<String, Object> result = (HashMap<String, Object>)xmlrpcLogin.execute("execute", write);
            return result;
        }
        catch (XmlRpcException e) {

            //logger.warn("XmlException Error while logging to OpenERP: ",e);
            System.out.println(e);
        }
        catch (Exception e){

            //logger.warn("Error while logging to OpenERP: ",e);
            System.out.println(e);
        }
        return null;

    }

    private Object[] search(String table, Vector<Object> param) {

        XmlRpcClient xmlrpcLogin = getXmlrpcLogin();

        try {
            Object search[] = new Object[6];
            search[0] = database;
            search[1] = userId;
            search[2] = password;
            search[3] = table;
            search[4] = "search";
            search[5] = param;

            Object[] ids = (Object[])xmlrpcLogin.execute("execute", search);
            return ids;
        }
        catch (XmlRpcException e) {
            //logger.warn("XmlException Error while logging to OpenERP: ",e);
            System.out.println(e);
        }
        catch (Exception e)
        {
            //logger.warn("Error while logging to OpenERP: ",e);
            System.out.println(e);
        }
        return null;
    }

    private HashMap<String, Object> read(String table, int id, Object[] fields) {

        XmlRpcClient xmlrpcLogin = getXmlrpcLogin();

        try {
            Object read[]=new Object[7];
            read[0] = database;
            read[1] = userId;
            read[2] = password;
            read[3] = table;
            read[4] = "read";
            read[5] = id;
            read[6] = fields;

            HashMap<String, Object> result = (HashMap<String, Object>)xmlrpcLogin.execute("execute", read);
            return result;
        }
        catch (XmlRpcException e) {
            //logger.warn("XmlException Error while logging to OpenERP: ",e);
            System.out.println(e);
        }
        catch (Exception e)
        {
            //logger.warn("Error while logging to OpenERP: ",e);
            System.out.println(e);
        }

        return null;
    }

    private XmlRpcClient getXmlrpcLogin() {

        XmlRpcClient xmlrpcLogin = new XmlRpcClient();

        XmlRpcClientConfigImpl xmlrpcConfigLogin = new XmlRpcClientConfigImpl();
        xmlrpcConfigLogin.setEnabledForExtensions(true);
        try {
            xmlrpcConfigLogin.setServerURL(new URL("http", host, port, "/xmlrpc/object"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        xmlrpcLogin.setConfig(xmlrpcConfigLogin);
        return xmlrpcLogin;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setDatabase(String database) {
        this.database = database;
    }

}
