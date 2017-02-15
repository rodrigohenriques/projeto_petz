package br.com.projeto.pets.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.Util;

public class HomeActivity extends AppCompatActivity implements ActivityImpl {

    private AppCompatImageView imgBackground;
    private AppCompatButton btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        bind();
        listners();
        init();
    }

    @Override
    public void bind() {
        imgBackground = (AppCompatImageView) findViewById(R.id.imgBackground);
        btnRegister = (AppCompatButton) findViewById(R.id.btnRegister);
        btnLogin = (AppCompatButton) findViewById(R.id.btnLogin);
    }

    @Override
    public void listners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
            }
        });
        btnRegister.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
                return false;
            }
        });
        btnLogin.setOnLongClickListener(new View.OnLongClickListener() {
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
        Glide.with(getActivity()).load(R.drawable.gato_retrato_hd).into(imgBackground);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

}
