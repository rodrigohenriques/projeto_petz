package br.com.projeto.pets.features.ad

import br.com.projeto.pets.infra.Store
import dagger.Module
import dagger.Provides

@Module
class AdModule {

  @Provides
  @AdScoped
  fun providesStore(): Store<AdState> = Store(AdState())

  @Provides
  fun providesAdHub(store: Store<AdState>, adRepository: AdRepository): AdContract.Hub {
    return AdHub(GetAdJob(store, adRepository))
  }
}
