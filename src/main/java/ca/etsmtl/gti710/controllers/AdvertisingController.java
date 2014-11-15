package ca.etsmtl.gti710.controllers;

import com.amazon.advertising.api.SignedRequestsHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/advertising")
public class AdvertisingController {

    @Value( "${amazon.aws_access_key_id}" )
    private String AWS_ACCESS_KEY_ID;

    @Value( "${amazon.aws_secret_key}" )
    private String AWS_SECRET_KEY;

    @Value( "${amazon.endpoint}" )
    private String ENDPOINT;

    @Value( "${amazon.associate_tag}" )
    private String ASSOCIATE_TAG;

    private SignedRequestsHelper helper;

    @RequestMapping("/accessories/{keyword}")
    public String accessories(@PathVariable("keyword") String _keyword) {

        String requestUrl;
        String keyword = _keyword.replace("-", " ");

        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemSearch");
        params.put("Keywords", keyword);
        params.put("SearchIndex", "Electronics");
        params.put("AssociateTag", ASSOCIATE_TAG);
        params.put("ResponseGroup", "Accessories");

        requestUrl = helper.sign(params);

        return httpGET(requestUrl);
    }

    @RequestMapping("/lookup/{asin}")
    public String accessory(@PathVariable("asin") String _asin) {

        String requestUrl;

        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemLookup");
        params.put("ItemId", _asin);
        params.put("AssociateTag", ASSOCIATE_TAG);
        params.put("ResponseGroup", "Small, Images");

        requestUrl = helper.sign(params);

        return httpGET(requestUrl);
    }

    private String httpGET(String req) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(req);
        String xmlResponse = "";

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            entity.writeTo(out);
            out.close();

            xmlResponse = out.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        final JSONObject jsonObject = XML.toJSONObject(xmlResponse);

        return jsonObject.toString();
    }
}
