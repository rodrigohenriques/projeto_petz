package br.com.projeto.pets.features.drawer

import android.app.Activity
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import br.com.projeto.pets.R

class DrawerManagerImpl(private val activity: Activity) : DrawerManager, NavigationView.OnNavigationItemSelectedListener {

  private lateinit var drawerLayout: DrawerLayout

  override fun setup() {
    activity.findViewById<NavigationView>(R.id.navigation)
        .setNavigationItemSelectedListener(this)

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
