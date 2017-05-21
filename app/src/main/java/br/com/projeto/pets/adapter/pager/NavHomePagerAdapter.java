package br.com.projeto.pets.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.UUID;

import br.com.projeto.pets.R;
import br.com.projeto.pets.fragment.AdoptionListFragment;
import br.com.projeto.pets.fragment.MockFragment;
import br.com.projeto.pets.fragment.SaleListFragment;
import br.com.projeto.pets.utils.CustomContext;

public class NavHomePagerAdapter extends FragmentPagerAdapter {

    public final static int VENDA_FGM = 0;
    public final static int ADOCAO_FGM = 1;


    public NavHomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case VENDA_FGM: {
                return SaleListFragment.newInstance(UUID.randomUUID().toString());
            }
            case ADOCAO_FGM: {
                return AdoptionListFragment.newInstance(UUID.randomUUID().toString());
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case VENDA_FGM: {
                return CustomContext.getContext().getResources().getString(R.string.lbl_sale);
            }
            case ADOCAO_FGM: {
                return CustomContext.getContext().getResources().getString(R.string.lbl_adoption);
            }
            default: {
                return null;
            }
        }
    }
}