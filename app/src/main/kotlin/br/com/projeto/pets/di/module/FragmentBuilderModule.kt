package br.com.projeto.pets.di.module

import br.com.projeto.pets.features.ad.AdFragment
import br.com.projeto.pets.features.ad.AdModule
import br.com.projeto.pets.features.ad.AdScoped
import br.com.projeto.pets.features.filter.fragment.AdoptionFilterFragment
import br.com.projeto.pets.features.filter.fragment.FilterScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @AdScoped
    @ContributesAndroidInjector(modules = arrayOf(AdModule::class))
    internal abstract fun adFragment(): AdFragment

    @FilterScoped
    @ContributesAndroidInjector
    internal  abstract fun adoptionFilterFragment(): AdoptionFilterFragment
}
