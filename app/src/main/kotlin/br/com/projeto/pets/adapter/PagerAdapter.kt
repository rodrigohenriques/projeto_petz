package br.com.projeto.pets.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.projeto.pets.fragment.SellingFragment


class CustomPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {

        when (position) {
            (0) -> {
                return SellingFragment()
            }
            (1) -> {
                return SellingFragment()
            }
            else -> {
                return SellingFragment()
            }
        }
    }

    override fun getCount(): Int = 2
}