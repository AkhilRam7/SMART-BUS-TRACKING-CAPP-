package com.example.koushik.capp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button blogin,bforgot;
    EditText conductor,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        blogin = (Button)findViewById(R.id.button1);
        bforgot = (Button)findViewById(R.id.button2);
        conductor = (EditText)findViewById(R.id.id2);
        pass=(EditText)findViewById(R.id.editText5);

        blogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String cond=conductor.getText().toString();
                String pass2=pass.getText().toString();
                if(cond.length()==0){
                    Toast.makeText(Login.this, "Conductor id is missing!",Toast.LENGTH_LONG).show();
                }
                else {
                    if (pass2.length() == 0) {
                        Toast.makeText(Login.this, "Password missing!", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(Login.this, Bus.class);
                        startActivity(intent);
                    }
                }
            }

        });

        bforgot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Login.this, Forgot.class);
                startActivity(intent1);

            }

        });
    }
}
