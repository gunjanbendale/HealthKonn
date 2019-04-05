package com.healthkonn.healthkonnect;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.healthkonn.healthkonnect.utils.SessionManagement;

public class FirebaseInstanceIdServiceClass extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("FirebaseInstanceService", "Firebase New Token: " + refreshedToken);
        SessionManagement sessionManagement;
        sessionManagement=new SessionManagement(getApplicationContext());
        sessionManagement.addfirebasetoken(refreshedToken);
    }
}