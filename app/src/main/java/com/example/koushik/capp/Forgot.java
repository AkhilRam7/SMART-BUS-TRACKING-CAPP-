package com.example.koushik.capp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgot extends AppCompatActivity {

    Button verify2;
    EditText code1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        verify2 = (Button)findViewById(R.id.button4);
        code1 = (EditText)findViewById(R.id.editText2);

        verify2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String code=code1.getText().toString();

                if (code.length()==0){
                    Toast.makeText(Forgot.this, "Please enter the CODE..!", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent6 = new Intent(Forgot.this, Forgot2.class);
                    startActivity(intent6);
                }
            }

        });
    }
}
