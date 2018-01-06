package br.com.projeto.pets.di.module

import br.com.projeto.pets.state.signup.SignUpStateManager
import br.com.projeto.pets.contract.SignUpContract
import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.di.ActivityScoped
import br.com.projeto.pets.presenter.SignUpPresenter
import br.com.projeto.pets.ui.activity.SignUpActivity
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
