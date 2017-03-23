package br.com.projeto.pets.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.PatternsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import br.com.projeto.pets.R;
import br.com.projeto.pets.model.User;
import br.com.projeto.pets.rest.endpoint.UserEndpoint;
import br.com.projeto.pets.rest.wrap.CallWrap;
import br.com.projeto.pets.rest.wrap.Delegate;
import br.com.projeto.pets.rest.wrap.Operation;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.Util;
import retrofit2.Call;

public class RegisterActivity extends AppCompatActivity implements ActivityImpl {

    private AppCompatImageView imgBackground;
    private AppCompatEditText edtName, edtEmail, edtEmailConfirm;
    private AppCompatButton btnCreateAccount;

    private ProgressDialog dialog = null;
    private static String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register);
        bind();
        listners();
        init();
    }

    @Override
    public void bind() {
        imgBackground = (AppCompatImageView) findViewById(R.id.imgBackground);
        edtName = (AppCompatEditText) findViewById(R.id.edtName);
        edtEmail = (AppCompatEditText) findViewById(R.id.edtEmail);
        edtEmailConfirm = (AppCompatEditText) findViewById(R.id.edtEmailConfirm);
        btnCreateAccount = (AppCompatButton) findViewById(R.id.btnCreateAccount);
    }

    @Override
    public void listners() {
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    goUserEndpoint_Create().execute();
                    dialog.show();
                }
            }
        });
        btnCreateAccount.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
                return false;
            }
        });
    }

    @Override
    public void init() {
        Util.colorStatusBar(this, getResources().getColor(R.color.accent3));
        Util.colorNavigationBar(this, getResources().getColor(R.color.accent3));
        Glide.with(getActivity()).load(R.drawable.lente_retrato_hd).into(imgBackground);
        dialog = Util.progressDialog(getActivity(), "Enviando...");
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private boolean validate() {
        if (TextUtils.isEmpty(edtName.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Nome' não pode ficar em branco").show();
            return false;
        }
        if (TextUtils.isEmpty(edtEmail.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Email' não pode ficar em branco").show();
            return false;
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(edtEmail.getText()).matches()) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Email' não é válido").show();
            return false;
        }
        if (TextUtils.isEmpty(edtEmailConfirm.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Confirmar Email' não pode ficar em branco").show();
            return false;
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(edtEmailConfirm.getText()).matches()) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Confirmar Email' não é válido").show();
            return false;
        }
        if (!edtEmail.getText().toString().equals(edtEmailConfirm.getText().toString())) {
            Util.showErrorSnack(getActivity()).setText("Os campos de Email não são iguais").show();
            return false;
        }

        return true;
    }

    public CallWrap<User> goUserEndpoint_Create(){
        final User user = User.newBuilder()
                .withName(edtName.getText().toString())
                .withEmail(edtEmail.getText().toString())
                .build();

        Call<User> call = new Operation<>(UserEndpoint.class).create()
                .create(user);

        return new CallWrap<>(call, new Delegate<User>() {
            @Override
            public void onSuccess(User object) {
                dialog.dismiss();
                Log.i(TAG, "onSuccess: " + object.toString());
                Util.showToast(getActivity(), "Para efetuar o cadastro, acesse o link do Email de confirmação será enviado para "+user.getEmail());
            }

            @Override
            public void onError(String message) {
                dialog.dismiss();
                Log.i(TAG, "onError: " + message);
                Util.showToast(getActivity(), "Para efetuar o cadastro, acesse o link do Email de confirmação será enviado para "+user.getEmail());
            }
        });
    }

}
