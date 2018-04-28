package br.com.projeto.pets.features.pet

import br.com.projeto.pets.di.module.NetworkModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class PetModule {

    @Provides
    fun providesPetActivity(activity: PetActivity): PetContract.View = activity

    @Provides
    fun providesPetApi(@Named(NetworkModule.LOGIN) retrofit: Retrofit): PetApi {
        return retrofit.create(PetApi::class.java)
    }

    @Provides
    fun providesPetPresenter(activity: PetActivity, petApi: PetApi): PetContract.Presenter = PetPresenter(activity, petApi)


}