package br.com.projeto.pets.features.filter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.MenuItem
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.ad.QueryParams
import br.com.projeto.pets.features.filter.fragment.AdoptionFilterFragment
import br.com.projeto.pets.features.filter.fragment.SaleFilterFragment
import br.com.projeto.pets.features.pet.FilterContract
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_filter.*
import javax.inject.Inject

class FilterActivity : DaggerAppCompatActivity(), FilterContract.View {

    @Inject
    lateinit var presenter: FilterContract.Presenter

    private val FILTER_STRING: String = "Filtro "
    private var queryParams: QueryParams? = QueryParams()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        queryParams = intent.extras.getSerializable("QUERY_PARAMS") as QueryParams?

        pager.adapter = PagerAdapter(this, supportFragmentManager, queryParams)
        pagerTitle.setupWithViewPager(pager)
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(pagerTitle))
        intent.extras.getString("TYPE").let { type -> filterChoice(type) }

        pagerTitle.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                filterChoice(tab!!.text as String)
            }

        })


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun filterChoice(adType: String) {
        when (adType) {
            AdType.ADOPTION.name -> {
                toolbar.title = FILTER_STRING + getString((AdType.ADOPTION.type))
                pager.currentItem = 1
            }
            getString(AdType.ADOPTION.type) -> {
                toolbar.title = FILTER_STRING + getString((AdType.ADOPTION.type))
            }
            else -> {
                toolbar.title = FILTER_STRING + getString((AdType.SELL.type))
                pager.currentItem = 0
            }
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED, intent)
        finish()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                setResult(RESULT_CANCELED, intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun getCallingIntent(context: Context, adType: AdType, queryParams: QueryParams?): Intent {
            val intent = Intent(context, FilterActivity::class.java)
            intent.putExtra("TYPE", adType.name)
            intent.putExtra("QUERY_PARAMS", queryParams)
            return intent
        }
    }
}


class PagerAdapter(
        private val context: Context,
        fragment: FragmentManager,
        private val queryParams: QueryParams? = null
) : FragmentStatePagerAdapter(fragment) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SaleFilterFragment.newInstance(queryParams)
            else -> AdoptionFilterFragment.newInstance(queryParams)
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
