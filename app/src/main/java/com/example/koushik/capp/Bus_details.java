package com.example.koushik.capp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Bus_details extends AppCompatActivity {
    int q;
    String pass;
    Button  reset,verify,passcount ;
    EditText code;

    TextView bus,vacant,occupied,source,destination,tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Bundle key = getIntent().getExtras();
        final String key1=key.getString("bus_id");
        Log.d("check",key1);*/
        final String key1="1";
        setContentView(R.layout.activity_bus_details);


        reset = (Button)findViewById(R.id.button6);
        passcount = (Button)findViewById(R.id.count);
        verify = (Button)findViewById(R.id.b);
        code = (EditText)findViewById(R.id.editText6);
        bus= (TextView)findViewById(R.id.textView8);
        occupied= (TextView)findViewById(R.id.textView10);
        vacant= (TextView)findViewById(R.id.textView9);

        source= (TextView)findViewById(R.id.textView11);
        destination= (TextView)findViewById(R.id.textView12);
        tickets= (TextView)findViewById(R.id.textView13);



        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetfunction();
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = code.getText().toString();

                verifyfunction(pass,key1);
                Log.d("enterx", pass);


            }
        });

        passcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpasscount();
            }
        });


    }

    public void resetfunction(){
        bus.setText("");
        //occupied.setText("");
        //vacant.setText("");
        source.setText("");
        destination.setText("");
        tickets.setText("");


    }

    private void verifyfunction(final String a, final String b) {
        /*String id = editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }*/
        // loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = "https://livebus.000webhostapp.com/"+ "verifyticket.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //loading.dismiss();
                Log.d("check9","onRespo");
                Toast.makeText(Bus_details.this,"ticket verified",Toast.LENGTH_LONG).show();
                showJSON2(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Bus_details.this,"invalid ticket",Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();

                //Log.d("First", "Start");
                //Log.d("First","");
                // Log.d("First", d.toString());

                parameters.put("tid", a);
                parameters.put("bid", b);


                //Log.d("First", "Stop");


                return parameters;
            }
        };

        RequestQueue requestQueue2 = Volley.newRequestQueue(Bus_details.this);
        requestQueue2.add(stringRequest);
    }


    public void showJSON2(String response){
        String [] src,dest,tcount;
        //String stopname="";
        //String address="";
        //String vc = "";
        //Log.d("check9","showJS");
        Log.d("check10","shJ2");
        try {
            Log.d("check10","try");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray results = jsonObject.getJSONArray("result");
            //buscount=results.length();
            //Log.d("checkbuscount",Integer.toString(buscount));
            src = new String[results.length()];
            dest = new String[results.length()];
            tcount = new String[results.length()];
            //stopid = new String[results.length()];
            Log.d("check10","try3");
            for(int i=0; i<results.length(); i++){
                JSONObject data = results.getJSONObject(i);
                src[i]= data.getString("source");
                dest[i]= data.getString("destination");
                tcount[i]= data.getString("tcount");
                //stopid[i]=data.getString(config.KEY_ID);
                Log.d("check10",src[i]);
                Log.d("check10",dest[i]);
                Log.d("check10",tcount[i]);
                //Log.d("check2","hello");
                //Log.d("check2",stopname[i]);
                //stopname = collegeData.getString(config.KEY_NAME);

            }
            source.setText(src[0]);
            destination.setText(dest[0]);
            tickets.setText(tcount[0]);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //textViewResult.setText("Name:\t"+name+"\nAddress:\t" +address+ "\nVice Chancellor:\t"+ vc);
    }

    private void getpasscount() {
        /*String id = editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }*/
        // loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = "http://192.168.43.50/passcount.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //loading.dismiss();
                Log.d("check9","onRespo");
                //Toast.makeText(Bus_details.this,"ticket verified",Toast.LENGTH_LONG).show();
                showJSON3(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Bus_details.this,"invalid ticket",Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();

                //Log.d("First", "Start");
                //Log.d("First","");
                // Log.d("First", d.toString());

                parameters.put("check", "check");
                //parameters.put("bid", b);


                //Log.d("First", "Stop");


                return parameters;
            }
        };

        RequestQueue requestQueue3 = Volley.newRequestQueue(Bus_details.this);
        requestQueue3.add(stringRequest);
    }

    public void showJSON3(String response){
        String [] value;
        //String stopname="";
        //String address="";
        //String vc = "";
        //Log.d("check9","showJS");
        Log.d("check10","shJ2");
        try {
            Log.d("check10","try");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray results = jsonObject.getJSONArray("result");
            //buscount=results.length();
            //Log.d("checkbuscount",Integer.toString(buscount));
            value = new String[results.length()];
            //dest = new String[results.length()];
            //tcount = new String[results.length()];
            //stopid = new String[results.length()];
            Log.d("check10","try3");
            for(int i=0; i<results.length(); i++){
                JSONObject data = results.getJSONObject(i);
                value[i]= data.getString("pcount");
                //dest[i]= data.getString("destination");
                //tcount[i]= data.getString("tcount");
                //stopid[i]=data.getString(config.KEY_ID);
                Log.d("check10",value[i]);
                //Log.d("check10",dest[i]);
                //Log.d("check10",tcount[i]);
                //Log.d("check2","hello");
                //Log.d("check2",stopname[i]);
                //stopname = collegeData.getString(config.KEY_NAME);

            }
            occupied.setText(value[0]);

            int vac = Integer.parseInt(value[0]);
            vac=60-vac;
            String str =Integer.toString(vac);
            vacant.setText(str);


            //destination.setText(dest[0]);
            //tickets.setText(tcount[0]);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //textViewResult.setText("Name:\t"+name+"\nAddress:\t" +address+ "\nVice Chancellor:\t"+ vc);
    }

}



