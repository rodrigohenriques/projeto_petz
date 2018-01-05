package br.com.projeto.pets.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.projeto.pets.R
import br.com.projeto.pets.extension.startActivity
import kotlinx.android.synthetic.main.activity_init.buttonLogin
import kotlinx.android.synthetic.main.activity_init.buttonSignUp

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_init)

    buttonSignUp.setOnClickListener {
      startActivity(SignUpActivity::class.java)
    }
    buttonLogin.setOnClickListener {
      startActivity(LoginActivity::class.java)
    }
  }
}
