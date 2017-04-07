package com.example.koushik.capp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgot2 extends AppCompatActivity {

    Button done;
    EditText password,confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot2);

        done = (Button)findViewById(R.id.button5);
        password= (EditText)findViewById(R.id.editText3);
        confirm= (EditText)findViewById(R.id.editText4);

        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String pass=password.getText().toString();
                String cpass=confirm.getText().toString();
                if (pass.length()==0){
                    Toast.makeText(Forgot2.this, "Please enter New Password..!", Toast.LENGTH_LONG).show();
                } else if (cpass.length()==0){
                    Toast.makeText(Forgot2.this, "Please enter your PAssword again..!", Toast.LENGTH_LONG).show();
                }else if (pass.length()<8){
                    Toast.makeText(Forgot2.this, "Password must be 8 characters!",Toast.LENGTH_LONG).show();
                }else  if (cpass.length()<8){
                    Toast.makeText(Forgot2.this,"Password must be 8 characters!",Toast.LENGTH_LONG).show();
                }
                else if (pass.equals(cpass)){
                    Toast.makeText(Forgot2.this,"Password change successful..",Toast.LENGTH_LONG).show();
                    Intent intent7 = new Intent(Forgot2.this,Bus.class);
                    startActivity(intent7);
                }else {
                    Toast.makeText(Forgot2.this,"Passwords do not match.",Toast.LENGTH_LONG).show();
                }



            }

        });
    }
}
