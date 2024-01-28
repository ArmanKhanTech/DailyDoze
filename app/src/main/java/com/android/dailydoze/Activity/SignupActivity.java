package com.android.dailydoze.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

@SuppressWarnings("ALL")
public class SignupActivity extends AppCompatActivity {
    Handler h = new Handler();
    private EditText email, pass;
    private Button submit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.signup_email);
        pass = findViewById(R.id.signup_password);

        submit = findViewById(R.id.signup_btn);

        submit.setOnClickListener(v -> registerNewUser());
    }

    private void registerNewUser() {
        String userEmail, userPass;

        userEmail = email.getText().toString();
        userPass = pass.getText().toString();

        if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPass)) {
            h.postDelayed(() -> submit.setText("Submit"), 3000);
            submit.setText("Invalid Inputs");
            return;
        }

        mAuth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                h.postDelayed(() -> submit.setText("Submit"), 3000);
                submit.setText("Successfull.");

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putBoolean("user", true);
                myEdit.putString("email", userEmail);
                myEdit.apply();

                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                h.postDelayed(() -> submit.setText("Submit"), 3000);
                submit.setText(Objects.requireNonNull(task.getException()).getLocalizedMessage());
            }
        });
    }

    public void signupBack(View v) {
        finish();
    }
}