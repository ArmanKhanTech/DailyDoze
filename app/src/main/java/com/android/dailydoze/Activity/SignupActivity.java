package com.android.dailydoze.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

@SuppressWarnings("ALL")
public class SignupActivity extends AppCompatActivity {
    private EditText email, pass;
    private Button submit;
    private FirebaseAuth mAuth;
    Handler h = new Handler();

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