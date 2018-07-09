package br.com.projeto.pets.features.profile

import dagger.Module
import dagger.Provides

@Module
class ProfileModule {

    @Provides
    fun providesProfileActivity(activity: ProfileActivity): ProfileContract.View =
            activity

    @Provides
    fun providesCreatePresenter(presenter: ProfilePresenter): ProfileContract.Presenter =
            presenter

}