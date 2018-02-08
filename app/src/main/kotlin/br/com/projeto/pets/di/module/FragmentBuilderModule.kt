package br.com.projeto.pets.di.module

import br.com.projeto.pets.di.FragmentScoped
import br.com.projeto.pets.features.ad.AdFragment
import br.com.projeto.pets.features.ad.AdModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = arrayOf(AdModule::class))
    internal abstract fun adFragment(): AdFragment
}
