package es.alonsoftware.newsapp.clases;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Jorge on 16/3/16.
 */
public class Noticia {

    private int id, medioId, categoria;
    private String titulo, nombre, url, texto, thumb, visual;
    private long published;

    public Noticia(JSONObject json) {

        try {
            this.id = json.getInt("id");
        } catch (JSONException e) {
            this.id = 0;
        }
        try {
            this.published = json.getLong("published");
        } catch (JSONException e) {
            this.published = 0;
        }
        try {
            this.medioId = json.getInt("medioId");
        } catch (JSONException e) {
            this.medioId = 0;
        }
        try {
            this.categoria = json.getInt("categoria");
        } catch (JSONException e) {
            this.categoria = 0;
        }
        try {
            this.visual = json.getString("visual");
        } catch (JSONException e) {
            this.visual = "";
        }

        try {
            this.titulo = json.getString("titulo");
        } catch (JSONException e) {
            this.titulo = "";
        }
        try {
            this.nombre = json.getString("nombre");
        } catch (JSONException e) {
            this.nombre = "";
        }
        try {
            this.url = json.getString("url");
        } catch (JSONException e) {
            this.url = "";
        }
        try {
            this.texto = json.getString("texto");
        } catch (JSONException e) {
            this.texto = "";
        }
        try {
            this.thumb = json.getString("thumb");
        } catch (JSONException e) {
            this.thumb = "";
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

    public String getUrl() {
        return url;
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


    public String getTexto(String colorFondo,String colorTexto) {
        String s = "<html><head><meta charset=\"UTF-8\"><style>body{color:"+colorTexto+";background-color:"+colorFondo+";}a{color:#58ACFA;}</style></head><body>";
        return s+texto.replace("<img ", "<img style=\"max-width:100%\"").replace("< img ", "<img style='max-width:100%'").replace("<iframe ", "<iframe style=\"max-width:100%\"").replace("< iframe ", "< iframe style=\"max-width:100%\"");
    }

    public String getTexto() {
        String s = "<html><head><meta charset=\"UTF-8\"><style>body{color:#FFFFFF;background-color:#000000;}a{color:#58ACFA;}</style></head><body>";
        s += "<h3 style='text-align:center;'>"+titulo+"</h3>";
        return s+texto.replace("<img ", "<img style=\"max-width:100%\"").replace("< img ", "<img style='max-width:100%'").replace("<iframe ", "<iframe style=\"max-width:100%\"").replace("< iframe ", "< iframe style=\"max-width:100%\"");
    }

    public String getTexto(String medio) {
        String s = "<html><head><meta charset=\"UTF-8\"><style>body{color:#FFFFFF;background-color:#000000;}a{color:#58ACFA;}</style></head><body>";
        s += "<h5 style='text-align:center;'>"+medio+"</h3>";
        s += "<h3 style='text-align:center;'>"+titulo+"</h3>";
        return s+texto.replace("<img ", "<img style=\"max-width:100%\"").replace("< img ", "<img style='max-width:100%'").replace("<iframe ", "<iframe style=\"max-width:100%\"").replace("< iframe ", "< iframe style=\"max-width:100%\"");
    }

    public String getThumb() {
        return thumb;
    }

    public String compartir(){
        return this.titulo+" "+this.url;
    }
}
