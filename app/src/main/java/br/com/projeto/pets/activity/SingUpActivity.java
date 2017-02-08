package br.com.projeto.pets.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.Util;

public class SingUpActivity extends AppCompatActivity implements ActivityImpl {

    private AppCompatImageView imgBackground;
    private AppCompatEditText edtName, edtAddress, edtCity, edtState, edtPhone;

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
        imgBackground = (AppCompatImageView) findViewById(R.id.imgBackground);
        edtName = (AppCompatEditText) findViewById(R.id.edtName);
        edtAddress = (AppCompatEditText) findViewById(R.id.edtAddress);
        edtCity = (AppCompatEditText) findViewById(R.id.edtCity);
        edtState = (AppCompatEditText) findViewById(R.id.edtState);
        edtPhone = (AppCompatEditText) findViewById(R.id.edtPhone);
    }

    @Override
    public void listners() {

    }

    @Override
    public void init() {
        Util.colorStatusBar(this, getResources().getColor(R.color.primary_dark));
        Util.colorNavigationBar(this, getResources().getColor(R.color.primary_dark));
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    public void createAccount(View view) {
    }

    public void login(View view) {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
    }
}
