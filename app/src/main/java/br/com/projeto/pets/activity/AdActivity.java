package br.com.projeto.pets.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;

import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.ActivityImpl;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdActivity extends AppCompatActivity implements ActivityImpl{

    CircleImageView imgUser01, imgUser02, imgUser03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ad);

        imgUser01 = (CircleImageView) findViewById(R.id.imgUser01);
        imgUser02 = (CircleImageView) findViewById(R.id.imgUser02);
        imgUser03 = (CircleImageView) findViewById(R.id.imgUser03);

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
    public void bind() {

    }

    @Override
    public void listners() {

    }

    @Override
    public void init() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
