package com.jakir.tryourappsby5timeappopeneveryday;

import android.content.Context;

//
// Created by JAKIR HOSSAIN on 3/13/2025.
//
public class DoRemoteJob {
    Context context;

    public DoRemoteJob(Context context) {
        this.context = context;

        tryOurOtherAppsLoad();
    }

    public void tryOurOtherAppsLoad() {
        String developerName = context.getString(R.string.developerName);
        new TryOurAppHelper().tryOurOtherAppsLoad(context, developerName, 5); // openCountWant means how much time app open then try to load
    }
}
