package ca.etsmtl.gti710.openErp;


import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

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

        XmlRpcClient xmlrpcLogin = new XmlRpcClient();

        XmlRpcClientConfigImpl xmlrpcConfigLogin = new XmlRpcClientConfigImpl();
        xmlrpcConfigLogin.setEnabledForExtensions(true);
        try {
            xmlrpcConfigLogin.setServerURL(new URL("http", host, port, "/xmlrpc/object"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        xmlrpcLogin.setConfig(xmlrpcConfigLogin);

        try {
            Object read[]=new Object[7];
            read[0]=database;   //Nom de la base de donnée
            read[1]=userId;   // ID de l'utilisateur
            read[2]=password;  //mot de passe

            //TODO Put those elements in the method parameters
            read[3]="product.product";
            read[4]="read";
            read[5] = product_id;
            read[6]=null;


            @SuppressWarnings("unchecked")
			HashMap<String, Object> result = (HashMap<String, Object>)xmlrpcLogin.execute("execute", read);
            System.out.println(result);
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

    public void createClient(String firstname, String lastname, String address, String city, String postalcode, String phone, String province, String country) {

        HashMap<String, Object> partnerInfo = new HashMap<String, Object>();
        partnerInfo.put("name", firstname + " " + lastname);
        partnerInfo.put("lang", "fr_FR");
        partnerInfo.put("street", address);
        partnerInfo.put("zip", postalcode);
        partnerInfo.put("city", city);
        partnerInfo.put("phone", phone);

        create("res.partner", partnerInfo);

    }

    //TODO find the country_id
    private int findCountry_id (String countryName) {

        return 0;
    }

    private void create(String table, HashMap<String, Object> info) {

        System.out.println("************************* ASKDJASKJDHKJASHD");

        XmlRpcClient xmlrpcLogin = new XmlRpcClient();

        XmlRpcClientConfigImpl xmlrpcConfigLogin = new XmlRpcClientConfigImpl();
        xmlrpcConfigLogin.setEnabledForExtensions(true);
        try {
            xmlrpcConfigLogin.setServerURL(new URL("http", host, port, "/xmlrpc/object"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        xmlrpcLogin.setConfig(xmlrpcConfigLogin);

        try {
            Object read[]=new Object[7];
            read[0] = database;
            read[1] = userId;
            read[2] = password;
            read[3] = table;
            read[4] = "create";
            read[5] = info;

            Object clientID = xmlrpcLogin.execute("execute", read);
            System.out.println(clientID);
        }
        catch (XmlRpcException e) {

            //logger.warn("XmlException Error while logging to OpenERP: ",e);
            System.out.println(e);
        }
        catch (Exception e){

            //logger.warn("Error while logging to OpenERP: ",e);
            System.out.println(e);
        }

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
