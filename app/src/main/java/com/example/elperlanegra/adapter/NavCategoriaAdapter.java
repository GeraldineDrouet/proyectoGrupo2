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
import com.example.elperlanegra.modelos.ModeloNavCategoria;

import java.util.List;

public class NavCategoriaAdapter extends RecyclerView.Adapter<NavCategoriaAdapter.ViewHolder> {

    Context context;
    List<ModeloNavCategoria> modeloNavCategoriaList;

    public NavCategoriaAdapter(Context context, List<ModeloNavCategoria> modeloNavCategoriaList) {
        this.context = context;
        this.modeloNavCategoriaList = modeloNavCategoriaList;
    }

    @NonNull
    @Override
    public NavCategoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoriaAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(modeloNavCategoriaList.get(position).getImg_url()).into(holder.categoriaImg);
        holder.nombre.setText(modeloNavCategoriaList.get(position).getNombre());
        holder.descripcion.setText(modeloNavCategoriaList.get(position).getDescripcion());
        holder.descuento.setText(modeloNavCategoriaList.get(position).getDescuento());
    }

    @Override
    public int getItemCount() {
        return modeloNavCategoriaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoriaImg;
        TextView nombre, descripcion, descuento;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoriaImg = itemView.findViewById(R.id.cat_nav_img);
            nombre = itemView.findViewById(R.id.nav_cat_nombre);
            descripcion = itemView.findViewById(R.id.nav_cat_descripcion);
            descuento = itemView.findViewById(R.id.nav_cat_descuento);
        }
    }
}
