package com.example.elperlanegra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {

    Button registro;
    EditText nombre, direccion, email, password;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registro = (Button) findViewById(R.id.reg_btn);
        nombre = (EditText) findViewById(R.id.name_reg);
        direccion = (EditText) findViewById(R.id.direccion_reg);
        email = (EditText) findViewById(R.id.email_reg);
        password = (EditText) findViewById(R.id.pass_reg);
        login = (TextView) findViewById(R.id.login_link);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistroActivity.this,LoginActivity.class));
            }
        });
    }
}