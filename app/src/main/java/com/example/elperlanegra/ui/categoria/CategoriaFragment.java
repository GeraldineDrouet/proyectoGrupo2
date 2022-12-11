package com.example.elperlanegra.ui.categoria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elperlanegra.R;
import com.example.elperlanegra.adapter.NavCategoriaAdapter;
import com.example.elperlanegra.adapter.PopularAdapters;
import com.example.elperlanegra.modelos.ModeloNavCategoria;
import com.example.elperlanegra.modelos.ModeloPopular;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CategoriaFragment extends Fragment {

    FirebaseFirestore db;
    RecyclerView rec_navCateg;
    List<ModeloNavCategoria> modeloNavCategoriaList;
    NavCategoriaAdapter navCategoriaAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categoria,container,false);

        db = FirebaseFirestore.getInstance();
        rec_navCateg = root.findViewById(R.id.cat_rec);
        rec_navCateg.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        modeloNavCategoriaList = new ArrayList<>();
        navCategoriaAdapter = new NavCategoriaAdapter(getActivity(),modeloNavCategoriaList);
        rec_navCateg.setAdapter(navCategoriaAdapter);

        db.collection("NavCategorias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ModeloNavCategoria modeloNavCategoria = document.toObject(ModeloNavCategoria.class);
                                modeloNavCategoriaList.add(modeloNavCategoria);
                                navCategoriaAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }


}