package kz.zangpro;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class GoToDB {

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams){
        URL url;
        String response = "";

        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line =  br.readLine()) != null){
                    response += line;
                }
            } else response = "";

        }catch (Exception e){
            e.printStackTrace();
        }


        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> param : params.entrySet()){
            if(first) first = false;
            else result.append("&");

            result.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(param.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
