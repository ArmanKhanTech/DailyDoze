package com.android.dailydoze.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.dailydoze.R;
import com.android.dailydoze.Service.GetAddressIntentService;
import com.android.dailydoze.Utility.Signup;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    //Declare Objects
    private EditText name, email, pass, phone, country, city, state, zip, address, blood, medical, other, height, weight, bp;
    RelativeLayout dob;
    Spinner gender, type;
    String gen, ty, selectedDate;
    private Button submit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Signup user;
    TextView dob_text;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    private LocationAddressResultReceiver addressResultReceiver;
    private Location currentLocation;
    private LocationCallback locationCallback;
    ImageView img;

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
        dob_text = findViewById(R.id.signup_dob_text);
        country = findViewById(R.id.signup_country);
        city = findViewById(R.id.signup_city);
        state = findViewById(R.id.signup_state);
        address = findViewById(R.id.signup_address);
        type = findViewById(R.id.signup_type);
        zip = findViewById(R.id.signup_zip);
        blood = findViewById(R.id.signup_bg);
        height = findViewById(R.id.signup_height);
        weight = findViewById(R.id.signup_weight);
        medical = findViewById(R.id.signup_medical);
        other = findViewById(R.id.signup_other);
        bp = findViewById(R.id.signup_bp);

        img = findViewById(R.id.select_location);

        dob.setOnClickListener(this::openDatePickerDialog);

        submit = findViewById(R.id.signup_btn);

        addressResultReceiver = new LocationAddressResultReceiver(new Handler());
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        img.setOnClickListener(v -> {
            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    currentLocation = locationResult.getLocations().get(0);
                    getAddress();
                }
            };
            startLocationUpdates();
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.gender, R.layout.spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinner_item);
        gender.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.type, R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_item);
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
                //
            }
        });

        loadDB loadDB = new loadDB();
        loadDB.execute();

        user = new Signup();

        submit.setOnClickListener(v -> {
            String userName = name.getText().toString();
            String userPhone = phone.getText().toString();
            String userPass = pass.getText().toString();
            String userEmail = email.getText().toString();
            String userGender = gen;
            String userDOB = selectedDate;
            String userCountry = country.getText().toString();
            String userCity = city.getText().toString();
            String userAddress = address.getText().toString();
            String userState = state.getText().toString();
            String userZip = zip.getText().toString();
            String userType = ty;
            String userBlood = blood.getText().toString();
            String userHeight = height.getText().toString();
            String userWeight = weight.getText().toString();
            String userMedical = medical.getText().toString();
            String userOther = other.getText().toString();
            String userBp = bp.getText().toString();

            if (userMedical.isEmpty()) {
                userMedical = "None";
            }

            if (userOther.isEmpty()) {
                userOther = "None";
            }

            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPass)
                    || TextUtils.isEmpty(userDOB) || TextUtils.isEmpty(userCity) || TextUtils.isEmpty(userState) || TextUtils.isEmpty(userCountry) ||
                    TextUtils.isEmpty(userAddress) || TextUtils.isEmpty(userZip) || TextUtils.isEmpty(userBlood) || TextUtils.isEmpty(userHeight)
                    || TextUtils.isEmpty(userWeight)) {
                Handler h = new Handler();
                h.postDelayed(() -> submit.setText("Submit"), 2000);
                submit.setText("Please Enter Correct Inputs");
            } else {
                addUser(userName, userPhone, userPass, userEmail, userGender, userDOB, userCountry,
                        userCity, userState, userAddress, userZip, userType, userBlood, userMedical,
                        userOther, userHeight, userWeight, userBp);
            }
        });
    }

    private void addUser(String name, String phone, String pass, String mail, String gender, String dob,
                         String cou, String ci, String sta, String add, String zi, String typ, String b, String medi, String oth,
                         String hei, String wei, String bp) {
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
        user.setUserHeight(hei);
        user.setUserWeight(wei);
        user.setUserBp(bp);

        databaseReference.child("users").orderByChild("userMail").equalTo(mail).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Handler h = new Handler();
                    h.postDelayed(() -> submit.setText("Submit"), 2000);
                    submit.setText("User Already Exists");
                } else {
                    String key = databaseReference.push().getKey();
                    assert key != null;
                    databaseReference.child("users").child(key).setValue(user);

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putBoolean("user", true);
                    myEdit.putString("name", name);
                    myEdit.putString("email", mail);
                    myEdit.apply();

                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //
            }
        });
    }

    public void openDatePickerDialog(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            dob_text.setText(selectedDate);
        }, 1990, 1, 1);

        datePickerDialog.show();
    }

    @SuppressLint("StaticFieldLeak")
    private final class loadDB extends AsyncTask<Void, Void, Void> {
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

    @SuppressWarnings("MissingPermission")
    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new
                            String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
        else {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    private class LocationAddressResultReceiver extends ResultReceiver {
        LocationAddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode == 0) {
                getAddress();
            }
            if (resultCode == 1) {
                Toast.makeText(SignupActivity.this, "Address not found., ", Toast.LENGTH_SHORT).show();
            }
            String currentAdd = resultData.getString("address_result");
            showResults(currentAdd);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getAddress() {
        if (!Geocoder.isPresent()) {
            Toast.makeText(SignupActivity.this, "Can't find the current address. ",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        Intent intent = new Intent(this, GetAddressIntentService.class);
        intent.putExtra("add_receiver", addressResultReceiver);
        intent.putExtra("add_location", currentLocation);
        startService(intent);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
    int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                Toast.makeText(this, "Location permission not granted restart the app if you want this feature.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void showResults(String currentAddress) {
        List<String> addressList = Arrays.asList(currentAddress.split(","));
        address.setText(addressList.get(0) + ", " + addressList.get(1) + "," + addressList.get(2));
        String tempCity = addressList.get(3);
        city.setText(tempCity.replaceAll(" ", ""));
        String temp = addressList.get(4);
        String[] splited = temp.split("\\s+");
        String tempState = splited[1];
        state.setText(tempState.replaceAll(" ", ""));
        zip.setText(splited[2]);
        String tempCountry = addressList.get(5);
        country.setText(tempCountry.replaceAll(" ", ""));
    }

    public void openLogin(View v){
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void signupBack(View v){
        finish();
    }
}