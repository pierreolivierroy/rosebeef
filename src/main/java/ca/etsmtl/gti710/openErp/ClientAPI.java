package ca.etsmtl.gti710.openErp;


import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.net.URL;
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
            //Connect
            params = new Object[] {database, login, password};
            Object id = xmlrpcLogin.execute("login", params);
            if (id instanceof Integer) {
                userId = (Integer)id;
            }
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
    }

    public HashMap<String, Object> readProduct(int product_id) {

        Object[] fields = new Object[6];
        fields[0] = "id";
        fields[1] = "name";
        fields[2] = "description";
        fields[3] = "default_code";
        fields[4] = "qty_available";
        fields[5] = "lst_price";


        return read("product.product", product_id, fields);
    }

    public HashMap<String, Object> readOrder(int order_id) {

        return read("sale.order", order_id, null);
    }

    public HashMap<String, Object> readLineOrder(Integer lineOrderId) {

        return read("sale.order.line", lineOrderId, null);
    }

    public int createOrder(int customerId){

        return 0;
    }

    public HashMap<String,Object> readCountry(int id) {

        return read("res.country", id, null);
    }


    public void createCustomer(String firstname, String lastname, String address, String city, String postalcode, String phone, String mobilePhone, String email, String province_id, String country_id) {

        HashMap<String, Object> partnerInfo = new HashMap<String, Object>();
        partnerInfo.put("name", firstname + " " + lastname);
        partnerInfo.put("lang", "fr_FR");
        partnerInfo.put("customer", true);

        Object id = create("res.partner", partnerInfo);

        HashMap<String, Object> addressInfo = new HashMap<String, Object>();
        partnerInfo.put("name", firstname + " " + lastname);
        addressInfo.put("partner_id", id);
        addressInfo.put("street", address);
        addressInfo.put("zip", postalcode);
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
