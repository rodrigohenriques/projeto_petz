package br.com.projeto.pets.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;

import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.Util;

public class DetailsScrollingActivity extends AppCompatActivity implements ActivityImpl{

    AppCompatImageView imgItemSale;
    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_details);
        bind();
        listners();
        init();
    }

    @Override
    public void bind() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgItemSale = (AppCompatImageView) findViewById(R.id.imgItemSale);
        fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    @Override
    public void listners() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void init() {
        setSupportActionBar(toolbar);

        Glide.with(this)
                .load(getIntent().getStringExtra("url"))
                .fitCenter()
                .into(imgItemSale);

        Util.colorNavigationBar(this, getResources().getColor(R.color.primary));

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
