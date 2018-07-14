package br.com.projeto.pets.di.module

import br.com.projeto.pets.features.main.ad.AdFragment
import br.com.projeto.pets.features.main.ad.AdModule
import br.com.projeto.pets.features.main.ad.AdScoped
import br.com.projeto.pets.features.filter.FilterModule
import br.com.projeto.pets.features.filter.fragment.AdoptionFilterFragment
import br.com.projeto.pets.features.filter.fragment.FilterScoped
import br.com.projeto.pets.features.filter.fragment.SaleFilterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @AdScoped
    @ContributesAndroidInjector(modules = arrayOf(AdModule::class,FilterModule::class))
    internal abstract fun adFragment(): AdFragment

    @FilterScoped
    @ContributesAndroidInjector(modules = arrayOf(FilterModule::class))
    internal  abstract fun adoptionFilterFragment(): AdoptionFilterFragment

    @FilterScoped
    @ContributesAndroidInjector(modules = arrayOf(FilterModule::class))
    internal  abstract fun saleFilterFragment(): SaleFilterFragment
}
