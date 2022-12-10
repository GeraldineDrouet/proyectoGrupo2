package com.example.elperlanegra.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elperlanegra.R;
import com.example.elperlanegra.adapter.CategInicioAdapter;
import com.example.elperlanegra.adapter.PopularAdapters;
import com.example.elperlanegra.adapter.RecomendadosAdapter;
import com.example.elperlanegra.modelos.ModeloCategoriasInicio;
import com.example.elperlanegra.modelos.ModeloPopular;
import com.example.elperlanegra.modelos.ModeloRecomendados;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;

    RecyclerView popularRec, inicioCatRec,recomendadosRec;
    FirebaseFirestore db;

    //Ofertas Tiempo Limitado items
    List<ModeloPopular> modeloPopularList;
    PopularAdapters popularAdapters;

    //Categorías Inicio
    List<ModeloCategoriasInicio> modeloCategoriasInicioList;
    CategInicioAdapter categInicioAdapter;

    //Recomendados
    List<ModeloRecomendados> modeloRecomendadosList;
    RecomendadosAdapter recomendadosAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        db = FirebaseFirestore.getInstance();

        popularRec = root.findViewById(R.id.pop_rec);
        inicioCatRec = root.findViewById(R.id.explore_rec);
        recomendadosRec = root.findViewById(R.id.recommended_rec);
        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.progressbarhome);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

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

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //para categorias de inicio
        inicioCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        modeloCategoriasInicioList = new ArrayList<>();
        categInicioAdapter = new CategInicioAdapter(getActivity(), modeloCategoriasInicioList);
        inicioCatRec.setAdapter(categInicioAdapter);

        db.collection("CategoriasInicio")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ModeloCategoriasInicio modeloCategoriasInicio = document.toObject(ModeloCategoriasInicio.class);
                                modeloCategoriasInicioList.add(modeloCategoriasInicio);
                                categInicioAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //para recomendados
        recomendadosRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        modeloRecomendadosList = new ArrayList<>();
        recomendadosAdapter = new RecomendadosAdapter(getActivity(), modeloRecomendadosList);
        recomendadosRec.setAdapter(recomendadosAdapter);

        db.collection("Recomendados")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ModeloRecomendados modeloRecomendados = document.toObject(ModeloRecomendados.class);
                                modeloRecomendadosList.add(modeloRecomendados);
                                recomendadosAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return root;
    }
}