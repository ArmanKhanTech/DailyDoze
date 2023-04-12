package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.R;
import com.android.dailydoze.Utility.Signup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {

    private EditText name, email, pass, phone, dob, country, city, state, zip, address, blood, medical, other;
    Spinner gender, type;
    String gen, ty;
    private Button submit;
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
        gender = findViewById(R.id.signup_gender);
        dob = findViewById(R.id.signup_dob);
        country = findViewById(R.id.signup_country);
        city = findViewById(R.id.signup_city);
        state = findViewById(R.id.signup_state);
        address = findViewById(R.id.signup_address);
        type = findViewById(R.id.signup_type);
        zip = findViewById(R.id.signup_zip);
        blood = findViewById(R.id.signup_bg);
        medical = findViewById(R.id.signup_medical);
        other = findViewById(R.id.signup_other);

        submit = findViewById(R.id.signup_btn);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.gender,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.type,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter2);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gen = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ty = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadDB loadDB = new loadDB();
        loadDB.execute();

        user = new Signup();

        // adding on click listener for our button.
        submit.setOnClickListener(v -> {

            String userName = name.getText().toString();
            String userPhone = phone.getText().toString();
            String userPass = pass.getText().toString();
            String userEmail = email.getText().toString();
            String userGender = gen;
            String userDOB = dob.getText().toString();
            String userCountry = country.getText().toString();
            String userCity = city.getText().toString();
            String userAddress = address.getText().toString();
            String userState = state.getText().toString();
            String userZip = zip.getText().toString();
            String userType = ty;
            String userBlood = blood.getText().toString();
            String userMedical = medical.getText().toString();
            String userOther = other.getText().toString();

            if(userMedical.isEmpty()){
                userMedical = "No";
            }

            if(userOther.isEmpty()){
                userOther = "No";
            }

            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPass)
                || TextUtils.isEmpty(userDOB) || TextUtils.isEmpty(userCity) || TextUtils.isEmpty(userState) || TextUtils.isEmpty(userCountry) ||
                TextUtils.isEmpty(userAddress) || TextUtils.isEmpty(userZip) || TextUtils.isEmpty(userBlood)) {
                Handler h = new Handler();
                h.postDelayed(() -> submit.setText("Submit"), 2000);
                submit.setText("Please Enter Correct Inputs");
            } else {
                addUser(userName, userPhone, userPass, userEmail, userGender, userDOB, userCountry,
                        userCity, userState, userAddress, userZip, userType, userBlood, userMedical,
                        userOther);
            }
        });
    }

    private void addUser(String name, String phone, String pass, String mail, String gender, String dob,
                         String cou, String ci, String sta, String add, String zi, String typ, String b, String medi, String oth) {
        user.setUserName(name);
        user.setUserPhone(phone);
        user.setUserMail(mail);
        user.setUserPass(pass);
        user.setUserGender(gender);
        user.setUserDOB(dob);
        user.setUserCountry(cou);
        user.setUserCity(ci);
        user.setUserState(sta);
        user.setUserAddress(add);
        user.setUserZip(zi);
        user.setUserType(typ);
        user.setUserMedical(medi);
        user.setUserOther(oth);
        user.setUserBlood(b);

        databaseReference.child("users").orderByChild("userMail").equalTo(mail).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Handler h = new Handler();
                    h.postDelayed(() -> submit.setText("Submit"), 2000);
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