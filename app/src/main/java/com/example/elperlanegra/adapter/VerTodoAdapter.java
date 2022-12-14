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
import com.example.elperlanegra.modelos.ModeloVerTodo;

import java.util.List;

public class VerTodoAdapter extends RecyclerView.Adapter<VerTodoAdapter.ViewHolder> {

    Context context;
    List<ModeloVerTodo> modeloVerTodoList;

    public VerTodoAdapter(Context context, List<ModeloVerTodo> modeloVerTodoList) {
        this.context = context;
        this.modeloVerTodoList = modeloVerTodoList;
    }

    @NonNull
    @Override
    public VerTodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ver_todo_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VerTodoAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(modeloVerTodoList.get(position).getImg_url()).into(holder.verTodo_img);
        holder.nombre.setText(modeloVerTodoList.get(position).getNombre());
        holder.descripcion.setText(modeloVerTodoList.get(position).getDescripcion());
        holder.raiting.setText(modeloVerTodoList.get(position).getRaiting());
        holder.precio.setText(modeloVerTodoList.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return modeloVerTodoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView verTodo_img;
        TextView nombre, descripcion, precio, raiting;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            verTodo_img = itemView.findViewById(R.id.ver_todo_img);
            nombre = itemView.findViewById(R.id.ver_todo_nombre);
            descripcion = itemView.findViewById(R.id.ver_todo_descripcion);
            raiting = itemView.findViewById(R.id.ver_todo_raiting);
            precio = itemView.findViewById(R.id.ver_todo_precio);
        }
    }
}
