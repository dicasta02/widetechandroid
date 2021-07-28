package com.example.widetech;

import android.app.Application;

import com.example.widetech.di.component.ApplicationComponent;

import com.example.widetech.di.module.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CustomApp extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        
        setUpRealm();
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this)).build();
        }

        return applicationComponent;
    }

    private void setUpRealm() {
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Widetech.db")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}