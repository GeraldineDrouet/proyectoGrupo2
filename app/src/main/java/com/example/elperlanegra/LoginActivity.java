package com.example.elperlanegra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText email, password;
    TextView registro;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        login = (Button) findViewById(R.id.login_btn);
        email = (EditText) findViewById(R.id.email_log);
        password = (EditText) findViewById(R.id.pass_log);
        registro = (TextView) findViewById(R.id.reg_link);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistroActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String CorreoUser = email.getText().toString();
        String ContrasenaUser = password.getText().toString();


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

        //INICIO DE SESIÓN
        auth.signInWithEmailAndPassword(CorreoUser,ContrasenaUser)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "¡INICIO DE SESIÓN EXITOSO!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Error: "+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}