package br.com.projeto.pets.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.projeto.pets.features.ad.AdFragment


class CustomPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {

        when (position) {
            (0) -> {
                return AdFragment()
            }
            (1) -> {
                return AdFragment()
            }
            else -> {
                return AdFragment()
            }
        }
    }

    override fun getCount(): Int = 2
}