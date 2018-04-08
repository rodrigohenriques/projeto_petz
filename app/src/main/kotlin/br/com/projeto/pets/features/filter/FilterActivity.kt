package br.com.projeto.pets.features.filter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.filter.fragment.AdoptionFilterFragment
import br.com.projeto.pets.features.filter.fragment.SaleFilterFragment
import br.com.projeto.pets.features.pet.FilterContract
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.ad_item.*
import javax.inject.Inject

class FilterActivity : DaggerAppCompatActivity(), FilterContract.View {

    @Inject
    lateinit var presenter: FilterContract.Presenter

    private val FILTER_STRING: String = "Filtro "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        intent.extras.getString("Type").let { type ->
            when (type) {
                AdType.SELL.toString() -> pager.currentItem = 0
                else -> pager.currentItem = 1
            }
        }


        pager.adapter = PagerAdapter(this, supportFragmentManager)
        pagerTitle.setupWithViewPager(pager)
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(pagerTitle))
        pagerTitle.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.text) {
                    getString((AdType.ADOPTION.type)) -> toolbar.title = FILTER_STRING + getString((AdType.ADOPTION.type))
                    else -> toolbar.title = FILTER_STRING + getString((AdType.SELL.type))
                }
            }

        })


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    companion object {
        fun getCallingIntent(context: Context, adType: AdType) {
            val intent = Intent(context, FilterActivity::class.java)
            intent.putExtra("TYPE", adType.toString())
            context.startActivity(intent)
        }
    }
}


class PagerAdapter(
        private val context: Context,
        fragment: FragmentManager
) : FragmentStatePagerAdapter(fragment) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SaleFilterFragment()
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
