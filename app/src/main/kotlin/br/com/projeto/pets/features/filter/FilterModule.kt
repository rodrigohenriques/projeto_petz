package br.com.projeto.pets.features.filter

import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.di.module.UserModule
import br.com.projeto.pets.features.filter.fragment.AdoptionFilterFragment
import br.com.projeto.pets.features.filter.fragment.FilterFragmentContract
import br.com.projeto.pets.features.filter.fragment.FilterFragmentPresenter
import br.com.projeto.pets.features.filter.fragment.SaleFilterFragment
import br.com.projeto.pets.features.pet.FilterContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(UserModule::class))
class FilterModule {

    @Provides
    fun provideFilterActivity(activity: FilterActivity): FilterContract.View = activity

    @Provides
    fun providesFilterPresenter(userRepository: UserRepository): FilterContract.Presenter = FilterPresenter(userRepository)

    @Provides
    fun providesFilterFragmentPresenter(): FilterFragmentContract.Presenter = FilterFragmentPresenter()

    @Provides
    fun provideAdoptionFilterFragment(fragment: AdoptionFilterFragment): AdoptionFilterFragment = fragment

    @Provides
    fun provideSaleFilterFragment(fragment: SaleFilterFragment): SaleFilterFragment = fragment

}
