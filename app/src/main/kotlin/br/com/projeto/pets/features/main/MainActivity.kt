package br.com.projeto.pets.features.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.Menu
import android.view.MenuItem
import br.com.projeto.pets.R
import br.com.projeto.pets.features.main.ad.AdFragment
import br.com.projeto.pets.features.main.ad.AdType
import br.com.projeto.pets.features.main.ad.QueryParams
import br.com.projeto.pets.features.create.CreateActivity
import br.com.projeto.pets.features.drawer.DrawerManager
import br.com.projeto.pets.features.filter.FilterActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.base_view.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    @Inject lateinit var drawerManager: DrawerManager
    private var queryParams: QueryParams? = null

    companion object {
        private const val FILTER_CODE = 707
        fun getCallingIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerManager.setup()

        toolbar.setNavigationIcon(R.drawable.ic_menu_black)
        setSupportActionBar(toolbar)

        pager.adapter = PagerAdapter(this, supportFragmentManager)
        pagerTitle.setupWithViewPager(pager)

        create_float.setOnClickListener {
            startActivity(CreateActivity.getCallingIntent(this))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_my_new_pet, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            (R.id.menu_filter) -> {
                startActivityForResult(FilterActivity.getCallingIntent(
                        this, adType = when (pager.currentItem) {
                    0 -> AdType.SELL
                    else -> AdType.ADOPTION
                }, queryParams = queryParams), FILTER_CODE)
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == FILTER_CODE && resultCode == Activity.RESULT_OK) {
            queryParams = data.extras.getSerializable("QUERY_PARAMS") as QueryParams?
            pager.adapter = PagerAdapter(this, supportFragmentManager, queryParams)
            pager.currentItem = respectiveTab(queryParams?.adType.toString())
            (pager.adapter as PagerAdapter).notifyDataSetChanged()
        } else if (requestCode == FILTER_CODE && resultCode == Activity.RESULT_CANCELED) {
            queryParams = QueryParams()
            pager.adapter = PagerAdapter(this, supportFragmentManager)
            (pager.adapter as PagerAdapter).notifyDataSetChanged()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerManager.openDrawer()
        return super.onSupportNavigateUp()
    }

    private fun respectiveTab(adType: String): Int =
            when (adType) {
                AdType.SELL.toString() -> 0
                else -> 1
            }
}

class PagerAdapter(
        private val context: Context,
        fragment: FragmentManager,
        private var data: QueryParams? = null
) : FragmentStatePagerAdapter(fragment) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AdFragment.newInstance(AdType.SELL, data)
            else -> AdFragment.newInstance(AdType.ADOPTION, data)
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
