package br.com.projeto.pets.di.component

import android.app.Application
import br.com.projeto.pets.PetsApplication
import br.com.projeto.pets.di.module.ActivityBuilderModule
import br.com.projeto.pets.di.module.ApplicationModule
import br.com.projeto.pets.di.module.InfraModule
import br.com.projeto.pets.di.module.NetworkModule
import br.com.projeto.pets.features.ad.AdModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        InfraModule::class,
        AdModule::class,
        ActivityBuilderModule::class))
interface ApplicationComponent : AndroidInjector<PetsApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

}
