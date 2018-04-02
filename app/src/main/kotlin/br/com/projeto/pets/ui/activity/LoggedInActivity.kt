package br.com.projeto.pets.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.projeto.pets.R
import br.com.projeto.pets.adapter.CustomPagerAdapter
import br.com.projeto.pets.fragment.SellingFragment
import kotlinx.android.synthetic.main.activity_logged_in.*
import kotlinx.android.synthetic.main.base_view.*

class LoggedInActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
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
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        pager.adapter = CustomPagerAdapter(supportFragmentManager)

        pagerTitle.setupWithViewPager(pager)
        pagerTitle.getTabAt(0)!!.text = "Venda"
        pagerTitle.getTabAt(1)!!.text = "Adoção"

    }

    companion object {
        fun getCallingIntent(context: Context) =
                Intent(context, LoggedInActivity::class.java)
                        .apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
    }
}
