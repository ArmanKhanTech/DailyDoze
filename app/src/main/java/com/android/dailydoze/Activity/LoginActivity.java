package com.android.dailydoze.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

@SuppressWarnings("All")
public class LoginActivity extends AppCompatActivity {
    private EditText email, pass;
    private Button submit;
    private FirebaseAuth mAuth;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.login_email);
        pass = findViewById(R.id.login_password);

        submit = findViewById(R.id.login_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount()
    {
        String userEmail, userPass;

        userEmail = email.getText().toString();
        userPass = pass.getText().toString();

        if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPass)) {
            h.postDelayed(() -> submit.setText("Submit"), 3000);
            submit.setText("Invalid Inputs");
            return;
        }

        mAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                h.postDelayed(() -> submit.setText("Submit"), 3000);
                submit.setText("Successfull");

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putBoolean("user", true);
                myEdit.putString("email", userEmail);
                myEdit.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            else {
                h.postDelayed(() -> submit.setText("Submit"), 3000);
                submit.setText(Objects.requireNonNull(task.getException()).getLocalizedMessage());
            }
        });
    }

    public void forgotPassword(View v) {
        String userEmail = email.getText().toString();
        Handler h = new Handler();

        if (TextUtils.isEmpty(userEmail)) {
            h.postDelayed(() -> submit.setText("Submit"), 3000);
            submit.setText("Invalid E-mail.");
            return;
        }

        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    h.postDelayed(() -> submit.setText("Submit"), 3000);
                    submit.setText("E-mail Sent");
                } else {
                    h.postDelayed(() -> submit.setText("Submit"), 3000);
                    submit.setText(Objects.requireNonNull(task.getException()).getLocalizedMessage());
                }
            }
        });
    }

    public void openSignup(View v) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}