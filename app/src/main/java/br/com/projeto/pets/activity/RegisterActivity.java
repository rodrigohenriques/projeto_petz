package br.com.projeto.pets.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.PatternsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;

import org.apache.commons.lang3.StringUtils;

import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.Util;

public class RegisterActivity extends AppCompatActivity implements ActivityImpl {

    private AppCompatImageView imgBackground;
    private AppCompatEditText edtUsername, edtPassword, edtEmail, edtEmailConfirm;
    private AppCompatButton btnCreateAccount;

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
        edtUsername = (AppCompatEditText) findViewById(R.id.edtUsername);
        edtPassword = (AppCompatEditText) findViewById(R.id.edtPassword);
        edtEmail = (AppCompatEditText) findViewById(R.id.edtEmail);
        edtEmailConfirm = (AppCompatEditText) findViewById(R.id.edtEmailConfirm);
        btnCreateAccount = (AppCompatButton) findViewById(R.id.btnCreateAccount);
    }

    @Override
    public void listners() {
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validade()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
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
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private boolean validade() {
        if (TextUtils.isEmpty(edtUsername.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Usuário' não pode ficar em branco").show();
            return false;
        }
        if (edtUsername.getText().toString().length() < 3) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Usuário' deve conter ao menos 3 caracteres").show();
            return false;
        }
        CharSequence cs = String.valueOf(edtUsername.getText().toString().charAt(0));
        if (StringUtils.isNumeric(cs)) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Usuário' não pode começar com um número").show();
            return false;
        }
        if (!StringUtils.isAlphanumeric(cs)) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Usuário' não pode começar com nenhum caractere especial").show();
            return false;
        }
        if (TextUtils.isEmpty(edtPassword.getText().toString().trim())) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Senha' não pode ficar em branco").show();
            return false;
        }
        if (edtPassword.getText().toString().length() < 6) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Senha' deve conter ao menos 6 caracteres").show();
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
            Util.showErrorSnack(getActivity()).setText("O campo 'Email Confirma' não pode ficar em branco").show();
            return false;
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(edtEmailConfirm.getText()).matches()) {
            Util.showErrorSnack(getActivity()).setText("O campo 'Email Confirma' não é válido").show();
            return false;
        }
        if (!edtEmail.getText().toString().equals(edtEmailConfirm.getText().toString())) {
            Util.showErrorSnack(getActivity()).setText("Os campos de 'Email' e 'Email Confirma' não são iguais").show();
            return false;
        }
        return true;
    }
}
