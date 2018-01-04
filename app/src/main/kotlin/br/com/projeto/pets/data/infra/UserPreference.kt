package br.com.projeto.pets.data.infra

import android.content.SharedPreferences
import br.com.projeto.pets.di.module.InfraModule.Companion.USER
import javax.inject.Inject
import javax.inject.Named

class UserPreference @Inject constructor(
        @Named(USER)
        private val preferences: SharedPreferences
) {

    fun saveToken(token: String) {
        preferences.edit()
                .putString(TOKEN, token)
                .apply()
    }

    companion object {
        private const val TOKEN = "token"
    }
}
