package br.com.projeto.pets.data.infra

import android.content.SharedPreferences

class UserPreference constructor(private val preferences: SharedPreferences) {

  fun saveToken(token: String) {
    preferences.edit()
        .putString(TOKEN, token)
        .apply()
  }

  fun getToken(): String = preferences.getString(TOKEN, "")

  fun isLogged() = preferences.contains(TOKEN)

  companion object {
    private const val TOKEN = "token"
  }
}
