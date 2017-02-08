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

    private RelativeLayout act_home;
    private AppCompatImageView imgBackground;

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
        act_home = (RelativeLayout) findViewById(R.id.act_home);

    }

    @Override
    public void listners() {

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

    public void register(View view) {
        startActivity(new Intent(getActivity(), RegisterActivity.class));
        overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
    }

    public void login(View view) {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        overridePendingTransition(R.anim.transac_out, R.anim.transac_in);
    }
}
