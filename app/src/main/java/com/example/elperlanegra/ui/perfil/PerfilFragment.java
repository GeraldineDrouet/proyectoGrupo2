package com.example.elperlanegra.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elperlanegra.R;
import com.example.elperlanegra.adapter.DatosActualesAdapter;
import com.example.elperlanegra.adapter.PopularAdapters;
import com.example.elperlanegra.modelos.ModeloDatosActualesUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class PerfilFragment extends Fragment {

    RecyclerView datosActualesRec;
    FirebaseDatabase db;
    DatabaseReference usersRef;
    FirebaseAuth auth;
    FirebaseUser user;

    List<ModeloDatosActualesUser> modeloDatosActualesUserList;
    DatosActualesAdapter datosActualesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil,container,false);

        db = FirebaseDatabase.getInstance();
        //modeloDatosActualesUserList = new ArrayList<ModeloDatosActualesUser>();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        usersRef = db.getReference("Users").child(auth.getUid());

        datosActualesRec = root.findViewById(R.id.datosActuales_rec);
        datosActualesRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        modeloDatosActualesUserList = new ArrayList<>();
        datosActualesAdapter = new DatosActualesAdapter(getActivity(),modeloDatosActualesUserList);
        datosActualesRec.setAdapter(datosActualesAdapter);

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                modeloDatosActualesUserList.clear();
                for(DataSnapshot snapshot :
                        dataSnapshot.getChildren()) {
                    ModeloDatosActualesUser user = dataSnapshot.getValue(ModeloDatosActualesUser.class);
                    modeloDatosActualesUserList.add(user);
                    String nombre = user.getNombre();
                    String direccion = user.getDireccion();
                    String correo = user.getCorreo();
                    String telefono = user.getTelefono();

                    String uid = dataSnapshot.getKey();
                } datosActualesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return root;
    }

}