package es.alonsoftware.newsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.alonsoftware.newsapp.R;
import es.alonsoftware.newsapp.clases.Noticia;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder> {

    private List<Noticia> mItems;

    public NoticiaAdapter(List<Noticia> items) {
        mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_noticia, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;
        public TextView titulo, subtitulo;
        public ImageView imagen;
        private Noticia status;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titulo = (TextView) itemView.findViewById(R.id.noticia_titulo);
            subtitulo = (TextView) itemView.findViewById(R.id.noticia_subtitulo);
            imagen = (ImageView) itemView.findViewById(R.id.noticia_imagen);
            context = itemView.getContext();

        }

        public void setData(Noticia item) {
            this.status = item;
            if (item == null)
                return;
            titulo.setText(status.getTitulo());
            if(!status.getThumb().equalsIgnoreCase(""))
              Picasso.with(context).load(status.getThumb()).into(imagen);
            subtitulo.setText(status.getFecha());
        }

        @Override
        public void onClick(View v) {

            /*Intent i = new Intent(context, InstagramStoryGalleryActivity.class);
            i.putExtra("user", status);
            context.startActivity(i);*/
        }
    }
}