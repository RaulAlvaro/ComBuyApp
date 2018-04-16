package com.example.raul.combuyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button btn_ingresar;
    private EditText et_user_name,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_ingresar = (Button) findViewById(R.id.btn_Ingresar);
        et_user_name = (EditText) findViewById(R.id.et_user_email);
        et_password = (EditText) findViewById(R.id.et_password);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }
}
