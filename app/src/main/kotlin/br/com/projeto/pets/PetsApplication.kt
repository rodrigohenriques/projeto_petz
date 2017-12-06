package br.com.projeto.pets

import br.com.projeto.pets.di.component.ApplicationComponent
import br.com.projeto.pets.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class PetsApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build()

        applicationComponent.inject(this)

        return applicationComponent
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }
}