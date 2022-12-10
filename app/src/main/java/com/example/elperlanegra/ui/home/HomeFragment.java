package com.example.elperlanegra.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elperlanegra.R;
import com.example.elperlanegra.adapter.CategInicioAdapter;
import com.example.elperlanegra.adapter.PopularAdapters;
import com.example.elperlanegra.databinding.FragmentHomeBinding;
import com.example.elperlanegra.modelos.CategoriasInicio;
import com.example.elperlanegra.modelos.ModeloPopular;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView popularRec, inicioCatRec;
    FirebaseFirestore db;

    //Ofertas Tiempo Limitado items
    List<ModeloPopular> modeloPopularList;
    PopularAdapters popularAdapters;

    //Categorías Inicio
    List<CategoriasInicio> categoriasInicioList;
    CategInicioAdapter categInicioAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        db = FirebaseFirestore.getInstance();

        popularRec = root.findViewById(R.id.pop_rec);
        inicioCatRec = root.findViewById(R.id.explore_rec);


        //para ofertas limitadas
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        modeloPopularList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(),modeloPopularList);
        popularRec.setAdapter(popularAdapters);

        db.collection("OfertasLimit")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ModeloPopular modeloPopular = document.toObject(ModeloPopular.class);
                                modeloPopularList.add(modeloPopular);
                                popularAdapters.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //para categorias de inicio
        inicioCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoriasInicioList = new ArrayList<>();
        categInicioAdapter = new CategInicioAdapter(getActivity(),categoriasInicioList);
        inicioCatRec.setAdapter(categInicioAdapter);

        db.collection("CategoriasInicio")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoriasInicio categoriasInicio = document.toObject(CategoriasInicio.class);
                                categoriasInicioList.add(categoriasInicio);
                                categInicioAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return root;
    }
}