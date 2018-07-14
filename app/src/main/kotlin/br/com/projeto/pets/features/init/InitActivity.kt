package br.com.projeto.pets.features.init

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.projeto.pets.BuildConfig
import br.com.projeto.pets.R
import br.com.projeto.pets.extension.startActivity
import br.com.projeto.pets.features.init.login.LoginActivity
import br.com.projeto.pets.features.init.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_init.buttonLogin
import kotlinx.android.synthetic.main.activity_init.buttonSignUp
import kotlinx.android.synthetic.main.activity_init.textViewVersion

class InitActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_init)

    buttonSignUp.setOnClickListener {
      startActivity(SignUpActivity::class.java)
    }
    buttonLogin.setOnClickListener {
      startActivity(LoginActivity::class.java)
    }

    textViewVersion.text = BuildConfig.VERSION_NAME
  }

  companion object {
    fun getCallingIntent(context: Context) = Intent(context, InitActivity::class.java)
  }
}
