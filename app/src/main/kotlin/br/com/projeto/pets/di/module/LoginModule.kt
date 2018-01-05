package br.com.projeto.pets.di.module

import br.com.projeto.pets.contracts.LoginContract
import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.presenters.LoginPresenter
import br.com.projeto.pets.view.activity.LoginActivity
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun provideLoginActivity(activity: LoginActivity): LoginContract.View = activity

    @Provides
    fun providesLoginPresenter(activity: LoginActivity, userRepository: UserRepository):
            LoginContract.Presenter {
        return LoginPresenter(activity, userRepository)
    }
}
