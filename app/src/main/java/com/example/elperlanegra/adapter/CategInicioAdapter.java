package com.example.elperlanegra.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.elperlanegra.R;
import com.example.elperlanegra.VerTodoActivity;
import com.example.elperlanegra.modelos.ModeloCategoriasInicio;

import java.util.List;

public class CategInicioAdapter extends RecyclerView.Adapter<CategInicioAdapter.ViewHolder> {

    Context context;
    List<ModeloCategoriasInicio> modeloCategoriasInicioList;

    public CategInicioAdapter(Context context, List<ModeloCategoriasInicio> modeloCategoriasInicioList) {
        this.context = context;
        this.modeloCategoriasInicioList = modeloCategoriasInicioList;
    }

    @NonNull
    @Override
    public CategInicioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inicio_cat_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategInicioAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(modeloCategoriasInicioList.get(position).getImg_url()).into(holder.catImg);
        holder.nombre.setText(modeloCategoriasInicioList.get(position).getNombre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VerTodoActivity.class);
                intent.putExtra("tipo",modeloCategoriasInicioList.get(holder.getAdapterPosition()).getTipo());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modeloCategoriasInicioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView catImg;
        TextView nombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg = itemView.findViewById(R.id.home_cat_img);
            nombre = itemView.findViewById(R.id.cat_home_name);
        }
    }
}
