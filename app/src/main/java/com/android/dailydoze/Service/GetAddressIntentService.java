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
            sendResultsToReceiver(0, msg);
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
            sendResultsToReceiver(1, msg);
        }
        else {
            Address address = addresses.get(0);
            String addressLine = address.getAddressLine(0);
            sendResultsToReceiver(2, addressLine);
        }
    }

    private void sendResultsToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString("address_result", message);
        addressResultReceiver.send(resultCode, bundle);
    }
}
