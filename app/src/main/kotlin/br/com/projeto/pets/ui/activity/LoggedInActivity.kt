package br.com.projeto.pets.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import br.com.projeto.pets.R
import br.com.projeto.pets.adapter.CustomPagerAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_logged_in.*
import kotlinx.android.synthetic.main.base_view.*

class LoggedInActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        pager.adapter = CustomPagerAdapter(supportFragmentManager)

        pagerTitle.setupWithViewPager(pager)
        pagerTitle.getTabAt(0)!!.text = "Venda"
        pagerTitle.getTabAt(1)!!.text = "Adoção"

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            (R.id.ads_filter) -> {

            }
            (R.id.profile) -> {

            }
            (R.id.ad) -> {

            }
            else -> {
                return false
            }
        }
        return true
    }

    companion object {
        fun getCallingIntent(context: Context) =
                Intent(context, LoggedInActivity::class.java)
                        .apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
    }
}
