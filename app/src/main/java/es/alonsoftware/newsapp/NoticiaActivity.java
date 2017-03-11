package es.alonsoftware.newsapp;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.liuguangqiang.progressbar.CircleProgressBar;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import org.json.JSONException;
import org.json.JSONObject;

import es.alonsoftware.newsapp.clases.FromUrl;
import es.alonsoftware.newsapp.clases.Noticia;
import es.alonsoftware.newsapp.clases.Titular;

public class NoticiaActivity extends AppCompatActivity implements View.OnClickListener{

    private CircleProgressBar progressBar;
    private SwipeBackLayout swipeBackLayout;
    private WebView webView;
    private Titular titular;
    private Noticia noticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);

        titular = (Titular) this.getIntent().getSerializableExtra("noticia");

        TextView titulo = (TextView) findViewById(R.id.titulo);
        titulo.setText(titular.getTitulo());

        progressBar = (CircleProgressBar) findViewById(R.id.circleProgressBar);
        webView = (WebView) findViewById(R.id.webView);

        swipeBackLayout = (SwipeBackLayout) findViewById(R.id.swipeBackLayout);
        swipeBackLayout.setEnableFlingBack(false);

        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.TOP);
        swipeBackLayout.setOnPullToBackListener(new SwipeBackLayout.SwipeBackListener() {
            @Override
            public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
                progressBar.setProgress((int) (progressBar.getMax() * fractionAnchor));
            }
        });

        findViewById(R.id.button_share).setOnClickListener(this);
        findViewById(R.id.button_web).setOnClickListener(this);
        progressBar.setOnClickListener(this);

        conectar();
    }

    public void conectar(){

        //progress.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);

        new Thread(){
            public void run(){

                try {
                    noticia = new Noticia(new JSONObject(FromUrl.get(Constantes.ENDPOINT+"noticia/" + titular.getId())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);

            }
        }.start();
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            //progress.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);

            if (noticia != null ){
                Resources res = getResources();
                String fondo = String.format("#%06X", 0xFFFFFF & res.getColor(R.color.fondoNoticia));
                String texto = String.format("#%06X", 0xFFFFFF & res.getColor(R.color.texto));
                webView.loadData(noticia.getTexto(fondo, texto), "text/html; charset=iso-UTF-8", null);
            }

        }

    };

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.button_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, noticia.compartir());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
                break;
            case R.id.button_web:

                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
                builder.setSecondaryToolbarColor(getResources().getColor(R.color.colorPrimaryDark));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(this, Uri.parse(noticia.getUrl()));

                break;
            case R.id.circleProgressBar:
                finish();
                break;
        }

    }
}
