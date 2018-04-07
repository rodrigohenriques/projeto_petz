package br.com.projeto.pets.features.filter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.filter.fragment.AdoptionFilterFragment
import br.com.projeto.pets.features.pet.FilterContract
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.base_view.*

class FilterActivity : DaggerAppCompatActivity() , FilterContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)


        pager.adapter = PagerAdapter(this, supportFragmentManager)
        pagerTitle.setupWithViewPager(pager)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, FilterActivity::class.java)
    }
}


class PagerAdapter(
        private val context: Context,
        fragment: FragmentManager
) : FragmentStatePagerAdapter(fragment) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AdoptionFilterFragment()
            else -> AdoptionFilterFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        val title = when (position) {
            0 -> AdType.SELL.type
            else -> AdType.ADOPTION.type
        }

        return context.getString(title)
    }

    override fun getCount() = AdType.values().size
}
