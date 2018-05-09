package br.com.projeto.pets.features.drawer

import android.app.Activity
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import br.com.projeto.pets.R
import br.com.projeto.pets.data.infra.UserPreference

class DrawerManagerImpl(private val activity: Activity, private val userPreference: UserPreference) : DrawerManager, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun setup() {

        val navView: NavigationView = activity.findViewById(R.id.navigation)
        val header: View = navView.getHeaderView(0)

        activity.findViewById<NavigationView>(R.id.navigation)
                .setNavigationItemSelectedListener(this)
        header.findViewById<TextView>(R.id.user_name).text= userPreference.getName()
        header.findViewById<TextView>(R.id.user_email).text = userPreference.getEmail()

        drawerLayout = activity.findViewById(R.id.drawer_layout)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }

    override fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }
}

interface DrawerManager {
    fun openDrawer()

    fun setup()
}
