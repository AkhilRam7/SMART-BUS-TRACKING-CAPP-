package com.example.koushik.capp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //private Button b;
    private TextView t,login,loc;
    private LocationManager locationManager;
    private LocationListener listener;
    double x,y;

    //String key="1";


    String a,b1;
    private  FirebaseAuth firebaseAuth;
   private FirebaseDatabase db;
    //private DatabaseReference myref;
    private DatabaseReference ref;
    //private Firebase myloc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final String key1="2";

        setContentView(R.layout.activity_main);
        //myloc = new Firebase("https://livebus-6a5f7.firebaseio.com/bus");
        db= FirebaseDatabase.getInstance();
        //myref=db.getReference();
        ref=db.getReference();
        t = (TextView) findViewById(R.id.t);
       // b = (Button) findViewById(R.id.b);
        loc=(TextView)findViewById(R.id.textView18);
        login=(TextView)findViewById(R.id.textView14);
        Log.d("check",key1);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        firebaseAuth = FirebaseAuth.getInstance();

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                x=location.getLatitude();
                y=location.getLongitude();
                t.setText("::Loading::");
               // int i = (int)(x*10000000);
                //int j= (int)(y*10000000);
                //updt(i,j);
                Log.d("check","lat"+ x);
                Log.d("check","long"+ y);
                t.setText("lat"+x+"long"+y);
                //String a= Integer.toString(i);

                        a= Double.toString(x);
                        //a = "hii";
                        b1= Double.toString(y);
                //b1= "bye";
              /* HashMap<String,String>dataMap = new HashMap<>();
                dataMap.put("Lat",a);
                dataMap.put("Long",b1);*/


                FirebaseUser user = firebaseAuth.getCurrentUser();

                //myref.setValue(dataMap);

                DatabaseReference usersRef ;

                usersRef=ref.child("id_"+key1);
                usersRef.child("lat").setValue(x);
                usersRef.child("lng").setValue(y);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        /*b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission

                locationManager.requestLocationUpdates("gps", 5000, 0, listener);
            }
        });*/

        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission

                locationManager.requestLocationUpdates("gps", 5000, 0, listener);
            }
        });
    }


    public void updt(int m,int n){
        //Intent next = new Intent(this,MapsActivity.class);
        //next.putExtra("long",m);
        //next.putExtra("lat",n);
        //startActivity(next);
    }




}

