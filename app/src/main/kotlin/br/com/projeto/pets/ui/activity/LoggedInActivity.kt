package br.com.projeto.pets.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.projeto.pets.R

class LoggedInActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_logged_in)
  }

  companion object {
    fun getCallingIntent(context: Context) =
        Intent(context, LoggedInActivity::class.java)
            .apply {
              flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
  }
}
