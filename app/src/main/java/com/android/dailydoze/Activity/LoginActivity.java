package com.android.dailydoze.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.dailydoze.R;
import com.android.dailydoze.Utility.Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button submit;
    private EditText email, pass;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Login user = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        submit = findViewById(R.id.login_btn);

        pass = findViewById(R.id.login_password);
        email = findViewById(R.id.login_email);

        LoadDB loadDB = new LoadDB();
        loadDB.execute();

        submit.setOnClickListener(v -> {

            String userPass = pass.getText().toString();
            String userEmail = email.getText().toString();

            if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPass)) {
                Handler h = new Handler();
                h.postDelayed(() -> submit.setText("Submit"), 2000);
                submit.setText("Please Enter Correct Inputs");
            } else {
                checkUser(userPass, userEmail);
            }
        });
    }

    private void checkUser(String pass, String mail) {
        user.setUserMail(mail);
        user.setUserPass(pass);

        databaseReference.child("users").orderByChild("email").equalTo(mail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()) {
                    Handler h = new Handler();
                    h.postDelayed(() -> submit.setText("Submit"), 2000);
                    submit.setText("Invaild Credentials");
                }else{
                    Map<String, Object> getData = (HashMap<String, Object>) snapshot.getValue();
                    String key = String.valueOf(getData.keySet());
                    key = key.substring(1, key.length()-1);
                    Map<String, Object> setData = (HashMap<String, Object>) getData.get(key);
                    String password = (String) setData.get("pass");

                    if(password.equals(pass)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putBoolean("user", true);
                        myEdit.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Handler h = new Handler();
                        h.postDelayed(() -> {
                            submit.setText("Submit");
                        }, 2000);
                        submit.setText("Invaild Password");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openSignup(View v){
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("StaticFieldLeak")
    private final class LoadDB extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            firebaseDatabase = FirebaseDatabase.getInstance("https://dailydoze-12db3-default-rtdb.asia-southeast1.firebasedatabase.app/");
            databaseReference = firebaseDatabase.getReference();

            return null;
        }

        @Override
        protected void onPostExecute(Void results) {
            super.onPostExecute(results);
        }
    }
}