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

public class LoginActivity extends AppCompatActivity implements ActivityImpl {

    private AppCompatImageView imgBackground;
    private AppCompatEditText edtEmail, edtPassword;
    private AppCompatButton btnEnter;

    private ProgressDialog dialog = null;
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        bind();
        listners();
        init();
    }

    @Override
    public void bind() {
        imgBackground = (AppCompatImageView) findViewById(R.id.imgBackground);
        edtEmail = (AppCompatEditText) findViewById(R.id.edtEmail);
        edtPassword = (AppCompatEditText) findViewById(R.id.edtPassword);
        btnEnter = (AppCompatButton) findViewById(R.id.btnEnter);
    }

    @Override
    public void listners() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {

                    String pass = edtPassword.getText().toString();
                    try {
                        pass = new MD5().gen(pass);
                    } catch (NoSuchAlgorithmException e) {
                        Util.showErrorAlert(getActivity(), "", e.getMessage());
                        e.printStackTrace();
                        return;
                    }
                    User user = User.newBuilder().withEmail(edtEmail.getText().toString()).withPassword(pass).build();

                    Call<User> call = new Operation<>(UserEndpoint.class).create()
                            .login(user);

                    CallWrap<User> callWrap = new CallWrap<>(call, new Delegate<User>() {
                        @Override
                        public void onSuccess(User object) {
                            dialog.dismiss();
                            Log.i(TAG, "onSuccess: " + object.toString());
                            Util.showErrorAlert(getActivity(), "MSG", object.toString());
                            startActivity(new Intent(getActivity(), SingUpActivity.class));
                            overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
                        }

                        @Override
                        public void onError(String message) {
                            dialog.dismiss();
                            if (message != null) {
                                Log.i(TAG, "onError: " + message);
                                Util.showErrorAlert(getActivity(), "MSG", message);
                            }
                        }
                    });

                    dialog.setMessage("Enviando os dados do usuário...");
                    dialog.show();
                    callWrap.execute();
                }
            }
        });
        btnEnter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(getActivity(), SingUpActivity.class));
                overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
                return false;
            }
        });
    }

    @Override
    public void init() {
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);


        Util.colorStatusBar(this, getResources().getColor(R.color.accent3));
        Util.colorNavigationBar(this, getResources().getColor(R.color.accent3));
        Glide.with(getActivity()).load(R.drawable.carro_retrato_hd).into(imgBackground);

        //edtEmail.setText("rdiego26@gmail.com");
        //edtPassword.setText("12345");
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    public boolean validate() {
        if (TextUtils.isEmpty(edtEmail.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Usuário' não pode ficar em branco").show();
            return false;
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {
            Util.showErrorSnack(getActivity()).setText("O 'E-Mail' informado não é válido").show();
            return false;
        }
        if (TextUtils.isEmpty(edtPassword.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Senha' não pode ficar em branco").show();
            return false;
        }
        if (edtPassword.getText().toString().trim().length() < getResources().getInteger(R.integer.password_limit)) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Senha' deve conter ao menos 4 caracteres").show();
            return false;
        }
        return true;
    }


}
