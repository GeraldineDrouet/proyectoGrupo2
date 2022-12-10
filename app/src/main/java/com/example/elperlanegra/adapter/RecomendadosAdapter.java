package com.example.elperlanegra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.elperlanegra.R;
import com.example.elperlanegra.modelos.ModeloRecomendados;

import java.util.List;

public class RecomendadosAdapter extends RecyclerView.Adapter<RecomendadosAdapter.ViewHolder> {

    Context context;
    List<ModeloRecomendados> recomendadosList;

    public RecomendadosAdapter(Context context, List<ModeloRecomendados> recomendadosList) {
        this.context = context;
        this.recomendadosList = recomendadosList;
    }

    @NonNull
    @Override
    public RecomendadosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recomendados_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecomendadosAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(recomendadosList.get(position).getImg_url()).into(holder.recomendadoImg);
        holder.nombre.setText(recomendadosList.get(position).getNombre());
        holder.descripcion.setText(recomendadosList.get(position).getDescripcion());
        holder.raiting.setText(recomendadosList.get(position).getRaiting());
        holder.precio.setText(recomendadosList.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return recomendadosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView recomendadoImg;
        TextView nombre, descripcion,raiting,precio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recomendadoImg = itemView.findViewById(R.id.rec_img);
            nombre = itemView.findViewById(R.id.rec_name);
            descripcion = itemView.findViewById(R.id.rec_dec);
            raiting = itemView.findViewById(R.id.rec_raiting);
            precio = itemView.findViewById(R.id.rec_price);
        }
    }
}
