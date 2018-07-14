package br.com.projeto.pets.features.main.ad

import br.com.projeto.pets.data.api.AdApi
import br.com.projeto.pets.data.repository.AdRepository
import br.com.projeto.pets.di.module.NetworkModule
import br.com.projeto.pets.infra.Store
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class AdModule {

  @Provides
  @AdScoped
  fun providesStore(): Store<AdState> = Store(AdState())

  @Provides
  fun providesAdHub(store: Store<AdState>, adRepository: AdRepository): AdContract.Hub {
    return AdHub(GetAdJob(store, adRepository))
  }

  @Provides
  fun provideAdApi(@Named(NetworkModule.LOGIN) retrofit: Retrofit): AdApi {
    return retrofit.create(AdApi::class.java)
  }
}
