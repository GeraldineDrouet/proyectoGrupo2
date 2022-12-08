package com.example.elperlanegra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elperlanegra.modelos.ModeloUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    Button registro;
    EditText nombre, direccion, email, password, telefono;
    TextView login;

    FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.progressbarreg);
        progressBar.setVisibility(View.GONE);

        registro = (Button) findViewById(R.id.reg_btn);
        nombre = (EditText) findViewById(R.id.name_reg);
        direccion = (EditText) findViewById(R.id.direccion_reg);
        email = (EditText) findViewById(R.id.email_reg);
        password = (EditText) findViewById(R.id.pass_reg);
        telefono = (EditText) findViewById(R.id.telf_reg);
        login = (TextView) findViewById(R.id.login_link);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUsuario();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistroActivity.this,LoginActivity.class));
            }
        });
    }

    private void crearUsuario() {
        String NombreUser = nombre.getText().toString();
        String DireccionUser = direccion.getText().toString();
        String CorreoUser = email.getText().toString();
        String ContrasenaUser = password.getText().toString();
        String TelefonoUser = telefono.getText().toString();

        if(TextUtils.isEmpty(NombreUser)){
            Toast.makeText(this,"¡Campo <Nombre y Apellido> está vacío!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(DireccionUser)){
            Toast.makeText(this,"¡Campo <Dirección> está vacío!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(CorreoUser)){
            Toast.makeText(this,"¡Campo <Correo electrónico> está vacío!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(ContrasenaUser)){
            Toast.makeText(this,"¡Campo <Contraseña> está vacío!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ContrasenaUser.length() < 6){
            Toast.makeText(this, "¡Contraseña debe tener más de 6 letras/dígitos!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(TelefonoUser)){
            Toast.makeText(this,"¡Campo <Teléfono> está vacío!", Toast.LENGTH_SHORT).show();
            return;
        }

        //CREACIÓN DE USUARIO
        auth.createUserWithEmailAndPassword(CorreoUser,ContrasenaUser)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            ModeloUser modeloUser = new ModeloUser(NombreUser,DireccionUser,CorreoUser,ContrasenaUser,TelefonoUser);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(modeloUser);

                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistroActivity.this, "¡REGISTRO EXITOSO!", Toast.LENGTH_SHORT).show();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistroActivity.this, "ERROR:"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}