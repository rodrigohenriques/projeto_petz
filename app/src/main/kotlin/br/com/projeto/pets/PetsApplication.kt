package br.com.projeto.pets

import br.com.projeto.pets.di.component.ApplicationComponent
import br.com.projeto.pets.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.paperdb.Paper
import timber.log.Timber

class PetsApplication : DaggerApplication() {
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    applicationComponent = DaggerApplicationComponent.builder()
        .application(this)
        .build()

    applicationComponent.inject(this)

    return applicationComponent
  }

  override fun onCreate() {
    super.onCreate()
    Paper.init(this)
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      // switch to crashlytics tree
      Timber.plant(Timber.DebugTree())
    }
  }

  companion object {
    lateinit var applicationComponent: ApplicationComponent
  }
}
