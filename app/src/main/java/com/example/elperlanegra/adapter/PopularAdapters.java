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
import com.example.elperlanegra.modelos.ModeloPopular;

import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    private List<ModeloPopular> modeloPopularList;

    public PopularAdapters(Context context, List<ModeloPopular> modeloPopularList) {
        this.context = context;
        this.modeloPopularList = modeloPopularList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(modeloPopularList.get(position).getImg_url()).into(holder.popImg);
        holder.nombre.setText(modeloPopularList.get(position).getNombre());
        holder.descripcion.setText(modeloPopularList.get(position).getDescripcion());
        holder.raiting.setText(modeloPopularList.get(position).getRaiting());
        holder.descuento.setText(modeloPopularList.get(position).getDescuento());
    }

    @Override
    public int getItemCount() {
        return modeloPopularList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView nombre, descripcion, raiting, descuento;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_img);
            nombre = itemView.findViewById(R.id.pop_name);
            descripcion = itemView.findViewById(R.id.pop_des);
            raiting = itemView.findViewById(R.id.pop_raiting);
            descuento = itemView.findViewById(R.id.pop_descuento);
        }
    }
}
