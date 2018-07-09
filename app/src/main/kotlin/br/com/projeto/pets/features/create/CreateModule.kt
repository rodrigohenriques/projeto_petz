package br.com.projeto.pets.features.create

import br.com.projeto.pets.data.api.CreateApi
import br.com.projeto.pets.data.infra.UserPreference
import br.com.projeto.pets.di.module.NetworkModule.Companion.LOGIN
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class CreateModule {

    @Provides
    fun providesCreateActivity(activity: CreateActivity): CreateContract.View = activity

    @Provides
    fun providesCreatePresenter(presenter: CreatePresenter): CreateContract.Presenter = presenter

    @Provides
    fun providesCreateApi(@Named(LOGIN) retrofit: Retrofit): CreateApi =
            retrofit.create(CreateApi::class.java)

}