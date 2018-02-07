package br.com.projeto.pets.di.module

import br.com.projeto.pets.di.ActivityScoped
import br.com.projeto.pets.features.base.BaseActivity
import br.com.projeto.pets.features.base.BaseModule
import br.com.projeto.pets.ui.activity.LoginActivity
import br.com.projeto.pets.ui.activity.SignUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
    internal abstract fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(SignUpModule::class))
    internal abstract fun signUpActivity(): SignUpActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(BaseModule::class))
    internal abstract fun signBaseActivity(): BaseActivity
}
