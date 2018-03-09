package br.com.projeto.pets.features.base

import br.com.projeto.pets.features.drawer.DrawerManager
import br.com.projeto.pets.features.drawer.DrawerManagerImpl
import dagger.Module
import dagger.Provides

@Module
class BaseModule {

  @Provides
  fun providesDrawerManager(baseActivity: BaseActivity): DrawerManager = DrawerManagerImpl(baseActivity)
}
