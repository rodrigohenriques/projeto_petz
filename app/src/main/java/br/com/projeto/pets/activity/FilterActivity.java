package br.com.projeto.pets.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.projeto.pets.R;
import br.com.projeto.pets.adapter.pager.FilterPagerAdapter;
import br.com.projeto.pets.fragment.AdoptionFilterFragment;
import br.com.projeto.pets.fragment.SaleFilterFragment;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.Util;

public class FilterActivity extends AppCompatActivity implements ActivityImpl,
        SaleFilterFragment.OnFragmentInteractionListener,
        AdoptionFilterFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_filter);
        bind();
        listners();
        init();
    }

    @Override
    public void bind() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    @Override
    public void listners() {

    }

    @Override
    public void init() {
        setSupportActionBar(toolbar);
        Util.colorStatusBar(this, getResources().getColor(R.color.primary_dark));
        Util.colorNavigationBar(this, getResources().getColor(R.color.primary));
        viewPager.setAdapter(new FilterPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
