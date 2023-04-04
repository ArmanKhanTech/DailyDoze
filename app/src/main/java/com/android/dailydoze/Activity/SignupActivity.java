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
import android.widget.TextView;

import com.android.dailydoze.R;

import com.android.dailydoze.Utility.Signup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {

    private EditText name, email, pass, phone;
    private Button submit;
    private TextView login;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Signup user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.signup_name);
        pass = findViewById(R.id.signup_password);
        email = findViewById(R.id.signup_email);
        phone = findViewById(R.id.signup_phone);

        submit = findViewById(R.id.signup_btn);

        login = findViewById(R.id.login_text);

        loadDB loadDB = new loadDB();
        loadDB.execute();

        user = new Signup();

        // adding on click listener for our button.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = name.getText().toString();
                String userPhone = phone.getText().toString();
                String userPass = pass.getText().toString();
                String userEmail = email.getText().toString();

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPass)) {
                    Handler h = new Handler();
                    h.postDelayed(() -> {
                        submit.setText("Submit");
                    }, 2000);
                    submit.setText("Please Enter Correct Inputs");
                } else {
                    addUser(userName, userPhone, userPass, userEmail);
                }
            }
        });
    }

    private void addUser(String name, String phone, String pass, String mail) {
        user.setUserName(name);
        user.setUserPhone(phone);
        user.setUserMail(mail);
        user.setUserPass(pass);

        databaseReference.child("users").orderByChild("userMail").equalTo(mail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Handler h = new Handler();
                    h.postDelayed(() -> {
                        submit.setText("Submit");
                    }, 2000);
                    submit.setText("User Already Exists");
                }else{
                    String key = databaseReference.push().getKey();
                    databaseReference.child("users").child(key).setValue(user);

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putBoolean("user", true);
                    myEdit.apply();

                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private final class loadDB extends AsyncTask<Void,Void,Void> {
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

    public void openLogin(View v){
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void signupBack(View v){
        finish();
    }
}