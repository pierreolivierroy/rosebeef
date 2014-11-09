package ca.etsmtl.gti710.controllers;

import com.amazon.advertising.api.SignedRequestsHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.XML;
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

    /*
    * Your AWS Access Key ID, as taken from the AWS Your Account page.
    */
    private static final String AWS_ACCESS_KEY_ID = "AKIAILRWM4275XJBHDFQ";

    /*
     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
     * Your Account page.
     */
    private static final String AWS_SECRET_KEY = "5NnKyCmU1QidSwQ5t9c/Wduoe4jg9ldFnjiq0Tof";

    /*
     * Use one of the following end-points, according to the region you are
     * interested in:
     *
     *      US: ecs.amazonaws.com
     *      CA: ecs.amazonaws.ca
     *      UK: ecs.amazonaws.co.uk
     *      DE: ecs.amazonaws.de
     *      FR: ecs.amazonaws.fr
     *      JP: ecs.amazonaws.jp
     *
     */
    private static final String ENDPOINT = "ecs.amazonaws.ca";

    /*
     * The Item ID to lookup. The value below was selected for the US locale.
     * You can choose a different value if this value does not work in the
     * locale of your choice.
     */
    private static final String ITEM_ID = "0545010225";

    /*
             * Set up the signed requests helper
             */
    private SignedRequestsHelper helper;

    @RequestMapping("/")
    public String ads() {

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
        params.put("Operation", "SimilarityLookup");
        params.put("ItemId", "B00004GJVO");

        requestUrl = helper.sign(params);

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(requestUrl);
        String xmlResponse = "";

        try {
            HttpResponse response = httpClient.execute(httpGet);
//            StatusLine statusLine = response.getStatusLine();

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
}
