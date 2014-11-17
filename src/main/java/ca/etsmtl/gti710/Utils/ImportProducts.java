package ca.etsmtl.gti710.Utils;

import java.io.*;


import ca.etsmtl.gti710.openErp.ClientAPI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ImportProducts {


    public void run(ClientAPI client) {

        String path = "phones"; //The path is in /bin from apache
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        JSONParser parser = new JSONParser();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".json")) {

                Object obj = null;
                try {
                    obj = parser.parse(new FileReader(path+ "/" + file.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                JSONObject jsonObject = (JSONObject) obj;

                final String name = jsonObject.get("name").toString();

                final String description = jsonObject.get("description").toString();

                final String os = ((JSONObject) jsonObject.get("android")).get("os").toString();

                final JSONArray imagesJsonArray = (JSONArray) jsonObject.get("images");
                String[] imagesArray = {"", "", "", "", ""};

                for (int j = 0;j < imagesJsonArray.size();j++) {

                    if(j<5) {
                        imagesArray[j] = imagesJsonArray.get(j).toString();
                    }


                }

                final String camera = ((JSONObject) jsonObject.get("camera")).get("primary").toString();

                final JSONObject jsonDisplay = (JSONObject) jsonObject.get("display");
                final String display = jsonDisplay.get("screenSize").toString() + ", " + jsonDisplay.get("screenResolution").toString();
                final String weight = ((JSONObject) jsonObject.get("sizeAndWeight")).get("weight").toString();
                final Double price = ((Long)jsonObject.get("price")).doubleValue();
                final String code = jsonObject.get("id").toString();

                client.createProduct(name, description, imagesArray, os, camera, display, weight, code, price);
            }
        }
    }
}
