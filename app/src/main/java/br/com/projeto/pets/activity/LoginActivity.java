package br.com.projeto.pets.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.PatternsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
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
    private AppCompatCheckBox chkSaveUser;

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
        chkSaveUser = (AppCompatCheckBox) findViewById(R.id.chkSaveUser);
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

                    callWrapGet(user).execute();
                    dialog.show();
                }
            }
        });
        btnEnter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                activitySingUp(null);
                return false;
            }
        });
    }

    public void activityMock() {
        startActivity(new Intent(getActivity(), MockActivity.class));
        overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
    }

    public void activitySingUp(User user) {
        Intent intent = new Intent(getActivity(), SingUpActivity.class);
        if (user != null) {
            intent.putExtra(SingUpActivity.TAG_USER_NOT_COMPLETE, user);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
    }

    public CallWrap<User> callWrapGet(final User userParam) {
        final Call<User> callGet = new Operation<>(UserEndpoint.class).create()
                .get(userParam.getEmail());
        Log.w(TAG, "call: " + userParam.toString());


        return new CallWrap<>(callGet, new Delegate<User>() {
            @Override
            public void onSuccess(User userSuccess) {
                if (userSuccess != null) {
                    Log.i(TAG, "onSuccess: " + userSuccess.toString());

                    if (userSuccess.getActive()) {
                        //faz login
                        callWrapLogin(userParam).execute();
                    } else {
                        dialog.dismiss();
                        activitySingUp(userSuccess);
                        //Util.showErrorAlert(getActivity(), "MSG", "Usuário inativo");
                    }
                } else {//usuário inválido ou inexistente
                    onError("Usuário inválido ou inexistente");
                }

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
    }

    public CallWrap<User> callWrapLogin(final User userParam) {
        final Call<User> callLogin = new Operation<>(UserEndpoint.class).create()
                .login(userParam);
        return new CallWrap<>(callLogin, new Delegate<User>() {
            @Override
            public void onSuccess(final User userSuccess) {
                dialog.dismiss();
                Log.i(TAG, "onSuccess: " + userSuccess.toString());

                if (userSuccess.getActive()) {
                    // aqui checar tb se os dados do usuário estão completos
                    if (!needCompleteRegistration(userSuccess)) {//cadastro está completo
                        //Util.showErrorAlert(getActivity(), "MSG", "Login OK");
                        if (chkSaveUser.isChecked()) {//se está marcado faz persiste os dados
                            Util.delUser();//remove o usuário anterior
                            Util.setUser(userSuccess);//grava o usuário
                            Util.setFlgSaveUser(true);//grava a marcação da flg
                        } else {
                            //login ok sem dados salvos
                            Util.setUser(userSuccess);//grava o usuário logado
                            Util.delFlgSaveUser();//remove a flg de save user
                        }
                        activityMock();
                    } else {
                        activitySingUp(userSuccess);
                    }
                } else {
                    Util.showErrorAlert(getActivity(), "MSG", "Usuário inativo. \n" +
                            "A confirmação por e-mail não foi validada.");
                }

                //startActivity(new Intent(getActivity(), SingUpActivity.class));
                //overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
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

    }

    @Override
    public void init() {
        dialog = Util.progressDialog(getActivity(), "Enviando os dados do usuário...");

        Util.colorStatusBar(this, getResources().getColor(R.color.accent3));
        Util.colorNavigationBar(this, getResources().getColor(R.color.accent3));
        Glide.with(getActivity()).load(R.drawable.carro_retrato_hd).into(imgBackground);

        if (Util.getFlgSaveUser()) {//se teve que salvar o usuário antes
            edtEmail.setText(Util.getUser().getEmail());
            chkSaveUser.setChecked(true);
        }

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
        if (edtPassword.getText().toString().trim().length() < getResources().getInteger(R.integer.password_min_limit)) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Senha' deve conter ao menos 4 caracteres").show();
            return false;
        }
        return true;
    }

    public boolean needCompleteRegistration(User user) {
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return true;
        }

        if (user.getAddress() == null || user.getAddress().trim().isEmpty()) {
            return true;

        }
        if (user.getCity() == null || user.getCity().trim().isEmpty()) {
            return true;

        }
        if (user.getState() == null || user.getState().trim().isEmpty()) {
            return true;

        }
        if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
            return true;
        }
        return false;
    }

}
