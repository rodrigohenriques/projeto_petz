package br.com.projeto.pets.di.module

import br.com.projeto.pets.di.ActivityScoped
import br.com.projeto.pets.view.activity.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
    internal abstract fun loginActivity(): LoginActivity
}
