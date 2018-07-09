package br.com.projeto.pets.features.main

import br.com.projeto.pets.data.infra.UserPreference
import br.com.projeto.pets.features.drawer.DrawerManager
import br.com.projeto.pets.features.drawer.DrawerManagerImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun providesDrawerManager(baseActivity: MainActivity, userPreference: UserPreference):
            DrawerManager = DrawerManagerImpl(baseActivity, userPreference)
}
