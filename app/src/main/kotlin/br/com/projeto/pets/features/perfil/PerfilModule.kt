package br.com.projeto.pets.features.perfil

import br.com.projeto.pets.features.create.CreateApi
import dagger.Module
import dagger.Provides

@Module
class PerfilModule {

    @Provides
    fun providesPerfilActivity(activity: PerfilActivity): PerfilContract.View = activity

    @Provides
    fun providesCreatePresenter(activity: PerfilActivity, createApi: CreateApi): PerfilContract.Presenter = PerfilPresenter(activity)

}