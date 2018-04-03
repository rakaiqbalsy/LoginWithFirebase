package com.rpl1.informatika.rpl11;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class LoginLayer extends AppCompatActivity implements View.OnClickListener{

    private TextView daftar;
    private Button masuk;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layer);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            //MainActivity
            finish();
            Intent i  = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

        progressDialog = new ProgressDialog(this);

        editTextEmail = (EditText) findViewById(R.id.email);

        editTextPassword = (EditText) findViewById(R.id.password);

        masuk = (Button) findViewById(R.id.button_login);

        daftar = (TextView) findViewById(R.id.signUP);


        masuk.setOnClickListener(this);
        daftar.setOnClickListener(this);

    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter your password !!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User ....... ");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            //masuk
                            finish();
                            Intent i  = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == masuk) {
            userLogin();
        }
        if (view == daftar) {
            finish();
            Intent i = new Intent(getApplicationContext(), SignupLayer.class);
            startActivity(i);
        }
    }
}