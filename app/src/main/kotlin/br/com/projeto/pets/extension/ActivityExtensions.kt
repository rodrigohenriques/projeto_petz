package br.com.projeto.pets.extension

import android.app.Activity
import android.content.Intent

fun <T : Activity> Activity.startActivity(
    activityClass: Class<T>,
    addExtraConfigurations: Intent.() -> Unit = {}
) {
  val intent = Intent(this, activityClass)
  intent.addExtraConfigurations()
  startActivity(intent)
}
