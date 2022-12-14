package com.example.elperlanegra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.elperlanegra.adapter.VerTodoAdapter;
import com.example.elperlanegra.modelos.ModeloVerTodo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class VerTodoActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView verTodoRec;
    VerTodoAdapter verTodoAdapter;
    List<ModeloVerTodo> modeloVerTodoList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_todo);

        toolbar = findViewById(R.id.toolbar_view_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        String tipo = getIntent().getStringExtra("tipo");
        verTodoRec = findViewById(R.id.view_all_rec);
        verTodoRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));


        modeloVerTodoList = new ArrayList<>();
        verTodoAdapter = new VerTodoAdapter(this,modeloVerTodoList);
        verTodoRec.setAdapter(verTodoAdapter);


        /////////////////////Obtener Desayunos
        if (tipo != null && tipo.equalsIgnoreCase("desayuno")) {
            firestore.collection("AllProducts").whereEqualTo("tipo", "desayuno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ModeloVerTodo modeloVerTodo = documentSnapshot.toObject(ModeloVerTodo.class);
                        modeloVerTodoList.add(modeloVerTodo);
                        verTodoAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

            /////////////////////Obtener A la carta
            if (tipo != null && tipo.equalsIgnoreCase("carta")){
                firestore.collection("AllProducts").whereEqualTo("tipo","carta").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                            ModeloVerTodo modeloVerTodo = documentSnapshot.toObject(ModeloVerTodo.class);
                            modeloVerTodoList.add(modeloVerTodo);
                            verTodoAdapter.notifyDataSetChanged();
                        }
                    }
                });
        }

        /////////////////////Obtener Menú navideño
        if (tipo != null && tipo.equalsIgnoreCase("navidad")) {
            firestore.collection("AllProducts").whereEqualTo("tipo", "navidad").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ModeloVerTodo modeloVerTodo = documentSnapshot.toObject(ModeloVerTodo.class);
                        modeloVerTodoList.add(modeloVerTodo);
                        verTodoAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        /////////////////////Obtener parrilladas
        if (tipo != null && tipo.equalsIgnoreCase("parrillada")) {
            firestore.collection("AllProducts").whereEqualTo("tipo", "parrillada").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ModeloVerTodo modeloVerTodo = documentSnapshot.toObject(ModeloVerTodo.class);
                        modeloVerTodoList.add(modeloVerTodo);
                        verTodoAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}