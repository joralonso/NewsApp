package es.alonsoftware.newsapp.clases;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import es.alonsoftware.newsapp.Constantes;

/**
 * Created by jorge on 11/3/17.
 */

public class DAO {

    public static List<Titular> getCategoria(int categoria){
        List<Titular> titulares = new ArrayList<Titular>();

        try {
            JSONArray jarr = new JSONArray(FromUrl.get(Constantes.ENDPOINT+"noticias/categoria/"+categoria));
            for (int i = 0; i < jarr.length(); i++){
                titulares.add(new Titular(jarr.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return titulares;
    }
}
