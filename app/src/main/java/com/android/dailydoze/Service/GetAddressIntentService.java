package com.android.dailydoze.Service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import androidx.annotation.Nullable;

@SuppressWarnings("ALL")
public class GetAddressIntentService extends IntentService {
    private static final String IDENTIFIER = "GetAddressIntentService";
    private ResultReceiver addressResultReceiver;
    public GetAddressIntentService() {
        super(IDENTIFIER);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String msg;

        addressResultReceiver = Objects.requireNonNull(intent).getParcelableExtra("add_receiver");

        if (addressResultReceiver == null) {
            Log.e("GetAddressIntentService", "No receiver, request not processing further.");
            return;
        }

        Location location = intent.getParcelableExtra("add_location");

        if (location == null) {
            msg = "No location cannot go further.";
            sendResultsToReceiver(0, msg, null, null, null, null);
            return;
        }

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        }
        catch (Exception ioException) {
            Log.e("", "Error in getting address for the location.");
        }

        if (addresses == null || addresses.size() == 0) {
            msg = "No address found for the given location.";
            sendResultsToReceiver(1, msg, null, null, null, null);
        } else {
            Address address = addresses.get(0);
            String country = address.getCountryName();
            String state = address.getAdminArea();
            String pin = address.getPostalCode();
            String city = address.getLocality();
            String permises = address.getSubLocality();
            permises += ", " + address.getSubAdminArea();
            sendResultsToReceiver(2, country, state, pin, city, permises);
        }
    }

    private void sendResultsToReceiver(int resultCode, String country, String state, String pin, String city, String permises) {
        Bundle bundle = new Bundle();
        bundle.putString("country", country);
        bundle.putString("state", state);
        bundle.putString("pin", pin);
        bundle.putString("city", city);
        bundle.putString("permises", permises);
        addressResultReceiver.send(resultCode, bundle);
    }
}
