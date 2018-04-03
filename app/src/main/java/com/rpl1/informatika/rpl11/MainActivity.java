package com.rpl1.informatika.rpl11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private Button buttonLogout;

    private TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            Intent i = new Intent(getApplicationContext(), LoginLayer.class);
            startActivity(i);
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewEmail.setText("Welcome "+user.getEmail());
        buttonLogout = (Button) findViewById(R.id.button_logout);

        buttonLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            Intent i = new Intent(getApplicationContext(),LoginLayer.class);
            startActivity(i);
            Toast.makeText(this, "Logout is Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}