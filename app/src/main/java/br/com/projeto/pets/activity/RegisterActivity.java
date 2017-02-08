package br.com.projeto.pets.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bumptech.glide.Glide;

import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.Util;

public class RegisterActivity extends AppCompatActivity implements ActivityImpl {

    private AppCompatImageView imgBackground;
    private AppCompatEditText edtUsername, edtPassword, edtEmail, edtEmailConfirm;

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
    }

    @Override
    public void listners() {

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

    public void createAccount(View view) {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
    }
}
