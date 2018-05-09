package br.com.projeto.pets.data.infra

import android.content.SharedPreferences

class UserPreference constructor(private val preferences: SharedPreferences) {

    fun saveToken(token: String) {
        preferences.edit()
                .putString(TOKEN, token)
                .apply()
    }

    fun saveName(name: String) {
        preferences.edit()
                .putString(NAME, name)
                .apply()
    }

    fun saveEmail(email: String) {
        preferences.edit()
                .putString(EMAIL, email)
                .apply()
    }

    fun getToken(): String = preferences.getString(TOKEN, "")
    fun getName(): String = preferences.getString(NAME, "")
    fun getEmail(): String = preferences.getString(EMAIL, "")

    fun isLogged() = preferences.contains(TOKEN)

    fun clear() {
        preferences.edit()
                .clear()
                .apply()
    }


    companion object {
        private const val TOKEN = "token"
        private const val NAME = "name"
        private const val EMAIL = "email"

    }
}
