package com.example.koushik.capp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bus extends AppCompatActivity {

    EditText service;
    Button next;
    String busid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        next = (Button)findViewById(R.id.button);
        service = (EditText)findViewById(R.id.editText);


        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String service1=service.getText().toString();
                if (service1.length()==0){
                    Toast.makeText(Bus.this, "Service number missing!",Toast.LENGTH_LONG).show();
                }
                //if (service1.length()!=0){
                else {
                    Intent intent3 = new Intent(Bus.this, Bus_details.class);
                    busid=service.getText().toString();
                    //intent3.putExtra("bus_id",busid);
                    startActivity(intent3);
                }
            }

        });
    }
}
