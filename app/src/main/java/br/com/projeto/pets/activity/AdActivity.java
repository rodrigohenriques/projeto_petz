package br.com.projeto.pets.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.Util;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdActivity extends AppCompatActivity implements ActivityImpl {

    private CircleImageView imgUser01, imgUser02, imgUser03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ad);
        bind();
        listners();
        init();
    }

    @Override
    public void bind() {
        imgUser01 = (CircleImageView) findViewById(R.id.imgUser01);
        imgUser02 = (CircleImageView) findViewById(R.id.imgUser02);
        imgUser03 = (CircleImageView) findViewById(R.id.imgUser03);
    }

    @Override
    public void listners() {
        Glide.with(this)
                .load(getString(R.string.url1))
                .dontAnimate()
                .into(imgUser01);
        Glide.with(this)
                .load(getString(R.string.url2))
                .dontAnimate()
                .into(imgUser02);
        Glide.with(this)
                .load(getString(R.string.url3))
                .dontAnimate()
                .into(imgUser03);
    }

    @Override
    public void init() {
        //Util.colorStatusBar(this, getResources().getColor(R.color.accent3));
        Util.colorNavigationBar(this, getResources().getColor(R.color.primary));
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
