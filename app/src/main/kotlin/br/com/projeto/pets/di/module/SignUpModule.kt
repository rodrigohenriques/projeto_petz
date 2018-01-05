package br.com.projeto.pets.di.module

import br.com.projeto.pets.SignUpStateManager
import br.com.projeto.pets.contracts.SignUpContract
import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.di.ActivityScoped
import br.com.projeto.pets.presenters.SignUpPresenter
import br.com.projeto.pets.view.activity.SignUpActivity
import dagger.Module
import dagger.Provides

@Module
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
}
