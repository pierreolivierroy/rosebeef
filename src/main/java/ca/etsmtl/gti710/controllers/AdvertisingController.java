package ca.etsmtl.gti710.controllers;

import com.amazon.advertising.api.SignedRequestsHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.XML;
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

    private String AWS_ACCESS_KEY_ID = "AKIAILRWM4275XJBHDFQ";

    private static final String AWS_SECRET_KEY = "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";

    private static final String ENDPOINT = "ecs.amazonaws.ca";

    private static final String ASSOCIATE_TAG = "pho0c88-20";


    private SignedRequestsHelper helper;

    @RequestMapping("/accessories")
    public String accessories() {

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
        params.put("Operation", "ItemSearch");
        params.put("Keywords", "iPhone 6");
        params.put("SearchIndex", "Electronics");
        params.put("AssociateTag", ASSOCIATE_TAG);
        params.put("ResponseGroup", "Accessories");

        requestUrl = helper.sign(params);

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(requestUrl);
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

        XML xmlHelper = new XML();
        final JSONObject jsonObject = xmlHelper.toJSONObject(xmlResponse);

        return jsonObject.toString();
    }

    @RequestMapping("/accessories/{asin}")
    public String accessory(@PathVariable("asin") String asin) {

        return "";
    }
}
