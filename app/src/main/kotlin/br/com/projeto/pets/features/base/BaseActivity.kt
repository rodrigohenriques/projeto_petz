package br.com.projeto.pets.features.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdFragment
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.drawer.DrawerManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.base_view.pager
import kotlinx.android.synthetic.main.base_view.pagerTitle
import kotlinx.android.synthetic.main.base_view.toolbar
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

    pager.adapter = PagerAdapter(supportFragmentManager)
    pagerTitle.setupWithViewPager(pager)
  }

  override fun onSupportNavigateUp(): Boolean {
    drawerManager.openDrawer()
    return super.onSupportNavigateUp()
  }

  companion object {
    fun getCallingIntent(context: Context) = Intent(context, BaseActivity::class.java)
  }
}

class PagerAdapter(fragment: FragmentManager) : FragmentStatePagerAdapter(fragment) {

  override fun getItem(position: Int): Fragment {
    return AdFragment.newInstance(AdType.SELL)
  }

  override fun getPageTitle(position: Int): CharSequence {
    return "Anúncios " + position
  }

  override fun getCount() = AdType.values().size
}
