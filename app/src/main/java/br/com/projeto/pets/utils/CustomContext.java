package br.com.projeto.pets.utils;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

public class CustomContext extends Application {

    private static Context context;


    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initRealm();
    }

    private void initRealm() {
        Realm.init(CustomContext.getContext());
    }
}
