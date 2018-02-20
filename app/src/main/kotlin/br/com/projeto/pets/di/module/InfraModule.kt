package br.com.projeto.pets.di.module

import android.content.Context
import android.content.SharedPreferences
import br.com.projeto.pets.data.infra.UserPreference
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
@Singleton
class InfraModule {

    @Provides
    @Named(USER)
    fun provideUserPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
    }

    @Provides
    fun providesUserPreference(@Named(USER) prefs: SharedPreferences): UserPreference = UserPreference(prefs)

    companion object {
        const val USER = "User"
        private const val USER_PREF = "userPref"

    }
}
