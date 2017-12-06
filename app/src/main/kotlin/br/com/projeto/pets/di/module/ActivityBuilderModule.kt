package br.com.projeto.pets.di.module

import br.com.projeto.pets.di.ActivityScoped
import br.com.projeto.pets.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    internal abstract fun mainActivity(): MainActivity
}
