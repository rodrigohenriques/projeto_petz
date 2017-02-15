package br.com.projeto.pets.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;

import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.Util;

public class LoginActivity extends AppCompatActivity implements ActivityImpl {

    private AppCompatImageView imgBackground;
    private AppCompatEditText edtUsername, edtPassword;
    private AppCompatButton btnEnter;

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
        edtUsername = (AppCompatEditText) findViewById(R.id.edtUsername);
        edtPassword = (AppCompatEditText) findViewById(R.id.edtPassword);
        btnEnter = (AppCompatButton) findViewById(R.id.btnEnter);
    }

    @Override
    public void listners() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    startActivity(new Intent(getActivity(), SingUpActivity.class));
                    overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
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
        Util.colorStatusBar(this, getResources().getColor(R.color.accent3));
        Util.colorNavigationBar(this, getResources().getColor(R.color.accent3));
        Glide.with(getActivity()).load(R.drawable.carro_retrato_hd).into(imgBackground);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    public boolean validate(){
        if(TextUtils.isEmpty(edtUsername.getText().toString().trim())){
            Util.showErrorSnack(getActivity()).setText("O campo 'Usuário' não pode ficar em branco").show();
            return false;
        }
        if(TextUtils.isEmpty(edtPassword.getText().toString().trim())){
            Util.showErrorSnack(getActivity()).setText("O campo 'Senha' não pode ficar em branco").show();
            return false;
        }
        return true;
    }
}
