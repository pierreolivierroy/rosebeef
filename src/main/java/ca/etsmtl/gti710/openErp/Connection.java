package ca.etsmtl.gti710.openErp;


import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class Connection {

    private String host = null;
    private int port = 0;
    private String login = null;
    private String password = null;
    private String database = null;
    
    public Connection() {
    }

    public int connect () {
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
            if (id instanceof Integer)
                return (Integer)id;
            return -1;
        }
        catch (XmlRpcException e) {
            //logger.warn("XmlException Error while logging to OpenERP: ",e);
        	System.out.println(e);
            return -2;
        }
        catch (Exception e)
        {
            //logger.warn("Error while logging to OpenERP: ",e);
        	System.out.println(e);
            return -3;
        }
    }

    public Vector<String> getDatabaseList() {
        XmlRpcClient xmlrpcDb = new XmlRpcClient();

        XmlRpcClientConfigImpl xmlrpcConfigDb = new XmlRpcClientConfigImpl();
        xmlrpcConfigDb.setEnabledForExtensions(true);
        try {
            xmlrpcConfigDb.setServerURL(new URL("http", host, port, "/xmlrpc/db"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        xmlrpcDb.setConfig(xmlrpcConfigDb);

        try {
            //Retrieve databases
            Vector<Object> params = new Vector<Object>();
            Object result = xmlrpcDb.execute("list", params);
            Object[] a = (Object[]) result;

            Vector<String> res = new Vector<String>();
            for (int i = 0; i < a.length; i++) {
                if (a[i] instanceof String) {
                    res.addElement((String) a[i]);
                }
            }
        }
        catch(Exception e)
        {
            //logger.warn("Error while retrieving OpenERP Databases: ", e);
            return null;
        }
        return null;

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
