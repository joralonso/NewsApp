package es.alonsoftware.newsapp.clases;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jorge on 25/2/17.
 */

public class Titular implements Serializable {

    private int id, medioId, categoria;
    private String titulo, nombre, visual, thumb;
    private long published;

    public Titular(JSONObject json){
        try {
            this.id = json.getInt("id");
        } catch (JSONException e) {
            id = 0;
        }
        try {
            this.categoria = json.getInt("categoria");
        } catch (JSONException e) {
            categoria = 0;
        }
        try {
            this.medioId = json.getInt("medioId");
        } catch (JSONException e) {
            medioId = 0;
        }
        try {
            this.titulo = json.getString("titulo");
        } catch (JSONException e) {
            titulo = "";
        }
        try {
            this.nombre = json.getString("nombre");
        } catch (JSONException e) {
            nombre = "";
        }
        try {
            this.visual = json.getString("visual");
        } catch (JSONException e) {
            visual = "";
        }
        try {
            this.thumb = json.getString("thumb");
        } catch (JSONException e) {
            thumb = "";
        }
        try {
            this.published = json.getLong("published");
        } catch (JSONException e) {
            published = 0L;
        }
    }

    public int getId() {
        return id;
    }

    public int getMedioId() {
        return medioId;
    }

    public int getCategoria() {
        return categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getVisual() {
        return visual;
    }

    public String getThumb() {
        return thumb;
    }

    public long getPublished() {
        return published;
    }

    public String getCateoriaNombre(){
        switch (categoria){
            case 0:
                return "Actualidad";
            case 1:
                return "Cultura";
            case 2:
                return "Tecnología";
            case 3:
                return "Deportes";
            case 4:
                return "Cine y Tele";
            case 5:
                return "Opinión";
            default:
                return "";
        }
    }

    public String getFecha(){

        Date date =new java.util.Date((long)published);
        Date now = new Date();

        long s = now.getTime() - date.getTime();
        s = s / 1000;
        Log.d("LATEST", ""+s+" "+now.getTime()+" - "+date.getTime());

        int temp = 0;

        if (s < (60*60)){

            temp = (int) s / (60);
            if (temp == 1)
                return "Hace 1 min.";
            else
                return String.format("Hace %d min.", temp);
        }else  if (s < 60*60*24){

            temp = (int) s / (60*60);
            return String.format("Hace %d horas.", temp);

        }else if (s < 60*60*24*30){

            temp = (int) s /  (60*60*24);
            return String.format("Hace %d días.", temp);

        }else{

            return String.format("Hace %d meses.", temp);
        }
    }
}
