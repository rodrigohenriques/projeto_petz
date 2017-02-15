package br.com.projeto.pets.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.projeto.pets.model.User;
import br.com.projeto.pets.R;
import br.com.projeto.pets.rest.endpoint.UserEndpoint;
import br.com.projeto.pets.rest.endpoint.ObjectEndpoint;
import br.com.projeto.pets.rest.wrap.CallWrap;
import br.com.projeto.pets.rest.wrap.Delegate;
import br.com.projeto.pets.rest.wrap.Operation;
import br.com.projeto.pets.utils.MD5;
import br.com.projeto.pets.utils.Util;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog dialog = null;

    private static String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = dialog();
    }

    public ProgressDialog dialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Carregando: " + "");

        return dialog;
    }


    public void login(View view) {
        String pass = null;
        try {
            pass = new MD5().gen("12345");
        } catch (NoSuchAlgorithmException e) {
            Util.showErrorAlert(this, "Erro", e.getMessage());
            e.printStackTrace();
            return;
        }
        User user = User.newBuilder().withEmail("rdiego26@gmail.com").withPassword(pass).build();

        Call<User> call = new Operation<>(UserEndpoint.class).create()
                .login(user);

        CallWrap<User> callWrap = new CallWrap<>(call, new Delegate<User>() {
            @Override
            public void onSuccess(User object) {
                dialog.dismiss();
                Log.i(TAG, "onSuccess: "+object.toString());
            }

            @Override
            public void onError(String message) {
                dialog.dismiss();
                Log.i(TAG, "onError: "+message);
            }
        });


        dialog.show();
        callWrap.execute();

    }


    public void badlogin(View view) {
        String pass = null;
        try {
            pass = new MD5().gen("12345");
        } catch (NoSuchAlgorithmException e) {
            Util.showErrorAlert(this, "Erro", e.getMessage());
            e.printStackTrace();
            return;
        }
        User user = User.newBuilder().withEmail("samirrolemberg@gmail.com").withPassword(pass).build();

        Call<User> call = new Operation<>(UserEndpoint.class).create()
                .login(user);

        CallWrap<User> callWrap = new CallWrap<>(call, new Delegate<User>() {
            @Override
            public void onSuccess(User object) {
                dialog.dismiss();
                Log.i(TAG, "onSuccess: "+object.toString());
            }

            @Override
            public void onError(String message) {
                dialog.dismiss();
                Log.i(TAG, "onError: "+message);
            }
        });


        dialog.show();
        callWrap.execute();
    }


    private void mock(){
        Call<List<Object>> call = new Operation<>(ObjectEndpoint.class).create()
                .listObject("param");
        CallWrap<List<Object>> callWrap = new CallWrap<>(call, new Delegate<List<Object>>() {
            @Override
            public void onSuccess(List<Object> object) {
                dialog.dismiss();
                Log.d(TAG, "onResponse: " + object.size());
                Log.d(TAG, "onResponse: " + object.toString());
//                Realm realm = RealmHelper.getInstance();
//
//                RealmList<StatusGovFullRealm> realms = new RealmList<>();
//
//                for (StatusGovFull model : object) {
//                    StatusGovFullRealm realmObj = new StatusGovFullRealm().fromObject(model);
//                    realms.add(realmObj);
//                }
//
//                realm.beginTransaction();
//                realm.copyToRealmOrUpdate(realms);
//                realm.commitTransaction();
            }

            @Override
            public void onError(String message) {
                dialog.dismiss();
                Log.e(TAG, "onError: " + message);
            }
        });


        dialog.show();
        callWrap.execute();

    }

    public void createUserMin(View view) {
        String pass = null;
        try {
            pass = new MD5().gen(">0130dfd0<");
        } catch (NoSuchAlgorithmException e) {
            Util.showErrorAlert(this, "Erro", e.getMessage());
            e.printStackTrace();
            return;
        }
        User user = User.newBuilder().withName("Samir Live").withEmail("srolemberg@live.com").withPassword(pass).build();

        Call<User> call = new Operation<>(UserEndpoint.class).create()
                .create(user);

        CallWrap<User> callWrap = new CallWrap<>(call, new Delegate<User>() {
            @Override
            public void onSuccess(User object) {
                dialog.dismiss();
                Log.i(TAG, "onSuccess: "+object.toString());
            }

            @Override
            public void onError(String message) {
                dialog.dismiss();
                Log.i(TAG, "onError: "+message);
            }
        });


        dialog.show();
        callWrap.execute();
    }
}
