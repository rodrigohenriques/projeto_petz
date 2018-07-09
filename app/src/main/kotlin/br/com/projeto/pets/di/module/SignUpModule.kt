package br.com.projeto.pets.di.module

import br.com.projeto.pets.features.init.signup.SignUpContract
import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.di.ActivityScoped
import br.com.projeto.pets.features.init.signup.SignUpPresenter
import br.com.projeto.pets.features.init.signup.SignUpState
import br.com.projeto.pets.features.init.signup.SignUpStateManager
import br.com.projeto.pets.features.init.signup.SignUpActivity
import dagger.Module
import dagger.Provides
import io.reactivex.Observable

@Module(includes = arrayOf(UserModule::class))
class SignUpModule {
  @Provides
  fun provideSignUpActivity(activity: SignUpActivity): SignUpContract.View = activity

  @Provides
  fun providesSignUpPresenter(
          activity: SignUpActivity,
          userRepository: UserRepository,
          signUpStateManager: SignUpStateManager
  ): SignUpContract.Presenter {
    return SignUpPresenter(activity, userRepository, signUpStateManager)
  }

  @Provides
  @ActivityScoped
  fun providesStateManager(): SignUpStateManager {
    return SignUpStateManager()
  }

  @Provides
  fun provicesStateChangesObservable(signUpStateManager: SignUpStateManager): Observable<SignUpState> {
    return signUpStateManager.stateChanges()
  }
}
