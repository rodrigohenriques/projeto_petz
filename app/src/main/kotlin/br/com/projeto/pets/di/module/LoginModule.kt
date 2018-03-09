package br.com.projeto.pets.di.module

import br.com.projeto.pets.contract.LoginContract
import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.presenter.LoginPresenter
import br.com.projeto.pets.ui.activity.LoginActivity
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(UserModule::class))
class LoginModule {

    @Provides
    fun provideLoginActivity(activity: LoginActivity): LoginContract.View = activity

    @Provides
    fun providesLoginPresenter(activity: LoginActivity, userRepository: UserRepository):
            LoginContract.Presenter {
        return LoginPresenter(activity, userRepository)
    }
}
