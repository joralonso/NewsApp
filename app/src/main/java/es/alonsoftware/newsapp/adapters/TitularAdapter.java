package es.alonsoftware.newsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.alonsoftware.newsapp.NoticiaActivity;
import es.alonsoftware.newsapp.R;
import es.alonsoftware.newsapp.clases.Titular;

public class TitularAdapter extends RecyclerView.Adapter<TitularAdapter.ViewHolder> {

    private List<Titular> mItems;

    public TitularAdapter(List<Titular> items) {
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
        public TextView titulo, subtitulo, categoria;
        public ImageView imagen;
        private Titular status;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titulo = (TextView) itemView.findViewById(R.id.noticia_titulo);
            subtitulo = (TextView) itemView.findViewById(R.id.noticia_subtitulo);
            imagen = (ImageView) itemView.findViewById(R.id.noticia_imagen);
            categoria = (TextView) itemView.findViewById(R.id.noticia_categoria);
            context = itemView.getContext();

        }

        public void setData(Titular item) {
            this.status = item;
            if (item == null)
                return;
            titulo.setText(status.getTitulo());

            if(!status.getThumb().equalsIgnoreCase(""))
              Picasso.with(context).load(status.getThumb()).into(imagen);
            else if (!status.getVisual().equalsIgnoreCase(""))
                Picasso.with(context).load(status.getVisual()).into(imagen);

            subtitulo.setText(status.getFecha());
            categoria.setText(status.getNombre());
        }

        @Override
        public void onClick(View v) {

            Intent i = new Intent(context, NoticiaActivity.class);
            i.putExtra("noticia", status);
            context.startActivity(i);
        }
    }
}