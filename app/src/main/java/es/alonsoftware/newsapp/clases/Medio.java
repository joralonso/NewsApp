package es.alonsoftware.newsapp.clases;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jorge on 25/2/17.
 */

public class Medio {

    private int id, categoria;
    private String nombre, feed;

    public Medio(JSONObject json) {
        try {
            this.id = json.getInt("id");
        } catch (JSONException e) {
            this.id = 0;
        }
        try {
            this.categoria = json.getInt("categoria");
        } catch (JSONException e) {
            this.categoria = 0;
        }
        try {
            this.nombre = json.getString("nombre");
        } catch (JSONException e) {
            this.nombre = "";
        }
        try {
            this.feed = json.getString("feed");
        } catch (JSONException e) {
            this.feed = "";
        }
    }

    public int getId() {
        return id;
    }

    public int getCategoria() {
        return categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFeed() {
        return feed;
    }
}