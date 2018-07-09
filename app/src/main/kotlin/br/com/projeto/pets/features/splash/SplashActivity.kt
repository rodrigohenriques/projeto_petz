package br.com.projeto.pets.features.splash

import android.os.Bundle
import br.com.projeto.pets.R
import br.com.projeto.pets.data.infra.UserPreference
import br.com.projeto.pets.features.main.MainActivity
import br.com.projeto.pets.features.init.InitActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var userPreference: UserPreference

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)

    if (userPreference.isLogged()) {
      startActivity(MainActivity.getCallingIntent(this))
    } else {
      startActivity(InitActivity.getCallingIntent(this))
    }

    finish()
  }
}
