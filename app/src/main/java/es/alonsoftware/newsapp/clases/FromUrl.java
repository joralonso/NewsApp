package es.alonsoftware.newsapp.clases;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Jorge on 5/11/15.
 */
public class FromUrl {

    public static String get(String _url){
        Log.d("FromURL", "Url: "+_url);
        String respuesta = "";
        try {

            URL url = new URL(_url+"?ckattempt=1");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "IMozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            respuesta = responseStrBuilder.toString();
            Log.d("FromURL", "Respuesta: "+respuesta);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
