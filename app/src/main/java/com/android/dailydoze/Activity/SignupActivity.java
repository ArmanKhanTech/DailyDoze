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
    ProgressBar progress;
    GoogleSignInClient googleSignInClient;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.signup_email);
        pass = findViewById(R.id.signup_password);

        submit = findViewById(R.id.signup_btn);

        progress = findViewById(R.id.progressBar);

        submit.setOnClickListener(v -> registerNewUser());
    }

    private void registerNewUser() {
        String userEmail, userPass;

        userEmail = email.getText().toString();
        userPass = pass.getText().toString();

        if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPass)) {
            h.postDelayed(() -> submit.setText("Submit"), 3000);
            submit.setText("Please Enter Correct Inputs.");

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
            } else {
                h.postDelayed(() -> submit.setText("Submit"), 3000);
                submit.setText(Objects.requireNonNull(task.getException()).getLocalizedMessage());
            }
        });
    }

    public void signingWithGoogle(View v) {
        progress.setVisibility(View.VISIBLE);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1052342056649-97eqh56un4d1n74n5ngrns2adtu1ieb1.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(SignupActivity.this, googleSignInOptions);

        Intent intent = googleSignInClient.getSignInIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (signInAccountTask.isSuccessful()) {
                GoogleSignInAccount googleSignInAccount = null;
                try {
                    googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
                if (googleSignInAccount != null) {
                    AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                    mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                h.postDelayed(() -> submit.setText("Submit"), 3000);
                                submit.setText("Successfull.");

                                FirebaseUser user = mAuth.getCurrentUser();

                                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putBoolean("user", true);
                                myEdit.putString("email", user.getEmail());
                                myEdit.apply();

                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                h.postDelayed(() -> submit.setText("Submit"), 3000);
                                submit.setText(Objects.requireNonNull(task.getException()).getLocalizedMessage());
                            }

                            progress.setVisibility(View.GONE);
                        }
                    });
                }
            }
        }
    }

    public void signupBack(View v) {
        finish();
    }
}