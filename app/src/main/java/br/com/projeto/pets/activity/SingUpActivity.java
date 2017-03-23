package br.com.projeto.pets.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.vicmikhailau.maskededittext.MaskedFormatter;
import com.vicmikhailau.maskededittext.MaskedWatcher;

import java.security.NoSuchAlgorithmException;

import br.com.projeto.pets.R;
import br.com.projeto.pets.model.User;
import br.com.projeto.pets.rest.endpoint.UserEndpoint;
import br.com.projeto.pets.rest.wrap.CallWrap;
import br.com.projeto.pets.rest.wrap.Delegate;
import br.com.projeto.pets.rest.wrap.Operation;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.MD5;
import br.com.projeto.pets.utils.Util;
import retrofit2.Call;

public class SingUpActivity extends AppCompatActivity implements ActivityImpl {

    private AppCompatButton btnCreateAccount;
    private AppCompatEditText edtName, edtAddress, edtCity, edtState, edtPhone, edtPassword, edtPasswordConfirm;

    public static String TAG_USER_NOT_COMPLETE = "TAG_USER_NOT_COMPLETE";

    public User userIncomplete;

    private MaskedFormatter formatter;
    private ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_singup);
        bind();
        listners();
        init();
    }

    @Override
    public void bind() {
        edtName = (AppCompatEditText) findViewById(R.id.edtName);
        edtAddress = (AppCompatEditText) findViewById(R.id.edtAddress);
        edtCity = (AppCompatEditText) findViewById(R.id.edtCity);
        edtState = (AppCompatEditText) findViewById(R.id.edtState);
        edtPhone = (AppCompatEditText) findViewById(R.id.edtPhone);
        edtPassword = (AppCompatEditText) findViewById(R.id.edtPassword);
        edtPasswordConfirm = (AppCompatEditText) findViewById(R.id.edtPasswordConfirm);
        btnCreateAccount = (AppCompatButton) findViewById(R.id.btnCreateAccount);
    }

    @Override
    public void listners() {
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userIncomplete != null) {//TODO: REMOVER NO FUTURO
                    createAccount();
                } else {
                    user(null);
                }
            }
        });
    }

    @Override
    public void init() {
        Util.colorStatusBar(this, getResources().getColor(R.color.primary_dark));
        Util.colorNavigationBar(this, getResources().getColor(R.color.primary_dark));


        formatter = new MaskedFormatter(getString(R.string.phone_pattern));
        edtPhone.addTextChangedListener(new MaskedWatcher(formatter, edtPhone));

        if (getIntent() != null) {
            if (getIntent().hasExtra(SingUpActivity.TAG_USER_NOT_COMPLETE)) {
                userIncomplete = (User) getIntent().getSerializableExtra(SingUpActivity.TAG_USER_NOT_COMPLETE);
            }
        }
        if (userIncomplete != null) {
            edtName.setText(userIncomplete.getName());
        }

        dialog = Util.progressDialog(getActivity(), "Enviando os dados do usuário...");
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private void createAccount() {
        if (validate()) {
            String md5 = null;
            try {
                md5 = new MD5().gen(edtPassword.getText().toString());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                Util.showErrorAlert(getActivity(), null, "onError: " + e.getMessage());
                return;
            }

            User completeUser = User.newBuilder(userIncomplete)
                    .withName(edtName.getText().toString())
                    .withAddress(edtAddress.getText().toString())
                    .withCity(edtCity.getText().toString())
                    .withState(edtState.getText().toString())
                    .withPhone(getPhoneUnmasked())
                    .withPassword(md5)
                    .withActive(true)
                    .build();

            Log.i(getString(R.string.tag), "completeUser: " + completeUser.toString());


            callWrapUpdate(completeUser).execute();
            dialog.show();
        }
    }

    public void user(View view) {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
    }

    private boolean validate() {
        if (TextUtils.isEmpty(edtName.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Nome' não pode ficar em branco").show();
            return false;
        }
        if (TextUtils.isEmpty(edtPassword.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Senha' não pode ficar em branco").show();
            return false;
        }
        if (edtPassword.getText().toString().length() < getResources().getInteger(R.integer.password_min_limit)) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Senha' deve conter ao menos 4 caracteres").show();
            return false;
        }
        if (TextUtils.isEmpty(edtPasswordConfirm.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Confirmar Senha' não pode ficar em branco").show();
            return false;
        }
        if (edtPasswordConfirm.getText().toString().length() < getResources().getInteger(R.integer.password_min_limit)) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Confirmar Senha' deve conter ao menos 4 caracteres").show();
            return false;
        }
        if (!edtPassword.getText().toString().equals(edtPassword.getText().toString())) {
            Util.showErrorSnack(getActivity()).setText("Os campo de Senha não são iguais").show();
            return false;
        }
        if (TextUtils.isEmpty(edtAddress.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Endereço' não pode ficar em branco").show();
            return false;
        }
        if (TextUtils.isEmpty(edtCity.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Cidade' não pode ficar em branco").show();
            return false;
        }
        if (TextUtils.isEmpty(edtState.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Estado' não pode ficar em branco").show();
            return false;
        }
        if (TextUtils.isEmpty(edtPhone.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Telefone' não pode ficar em branco").show();
            return false;
        }
        if (getPhoneUnmasked().length() < getResources().getInteger(R.integer.user_phone_limit_8)) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Telefone' não é válido").show();
            return false;
        }

        return true;
    }

    public CallWrap<Void> callWrapUpdate(final User userParam) {
        Call<Void> callUpdate = new Operation<>(UserEndpoint.class).create()
                .update(userParam.getId().toString(), userParam);

        return new CallWrap<>(callUpdate, new Delegate<Void>() {
            @Override
            public void onSuccess(Void userupdateSuccess) {
                dialog.dismiss();
                Log.i(getString(R.string.tag), "onSuccess: Void");
                Util.showErrorAlert(getActivity(), null, "onSuccess: " + userParam.toString());
            }

            @Override
            public void onError(String message) {
                dialog.dismiss();
                Log.i(getString(R.string.tag), "onError: " + message);
                Util.showErrorAlert(getActivity(), null, "onError: " + message);
            }
        });

    }

    private String getPhoneUnmasked() {
        return formatter.formatString(edtPhone.getText().toString()).getUnMaskedString();
    }
}
