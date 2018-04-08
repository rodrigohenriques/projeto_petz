package br.com.projeto.pets.features.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.Menu
import android.view.MenuItem
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdFragment
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.drawer.DrawerManager
import br.com.projeto.pets.features.filter.FilterActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.base_view.*
import javax.inject.Inject

class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var drawerManager: DrawerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        drawerManager.setup()

        toolbar.setNavigationIcon(R.drawable.ic_menu_black)
        setSupportActionBar(toolbar)

        pager.adapter = PagerAdapter(this, supportFragmentManager)
        pagerTitle.setupWithViewPager(pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_my_new_pet, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            (R.id.menu_filter) -> {
                FilterActivity.getCallingIntent(this,adType = AdType.SELL)
                return true
            }
            (R.id.menu_filter) -> {
                return true
            }
            else -> {
                return false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerManager.openDrawer()
        return super.onSupportNavigateUp()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, BaseActivity::class.java)
    }
}

class PagerAdapter(
        private val context: Context,
        fragment: FragmentManager
) : FragmentStatePagerAdapter(fragment) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AdFragment.newInstance(AdType.SELL)
            else -> AdFragment.newInstance(AdType.ADOPTION)
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
