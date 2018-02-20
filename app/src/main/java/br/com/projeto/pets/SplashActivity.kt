package br.com.projeto.pets

import android.os.Bundle
import br.com.projeto.pets.data.infra.UserPreference
import br.com.projeto.pets.features.base.BaseActivity
import br.com.projeto.pets.ui.activity.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var userPreference: UserPreference

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)


    if (userPreference.isLogged()) {
      startActivity(BaseActivity.getCallingIntent(this))
    } else {
      startActivity(MainActivity.getCallingIntent(this))
    }

    finish()
  }
}
