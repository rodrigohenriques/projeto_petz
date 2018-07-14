package br.com.projeto.pets.features.profile

import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.di.module.UserModule
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(UserModule::class))
class ProfileModule {
    @Provides
    fun providesProfileActivity(activity: ProfileActivity): ProfileContract.View =
            activity

    @Provides
    fun providesCreatePresenter(view: ProfileContract.View,
                                userRepository: UserRepository):
            ProfileContract.Presenter = ProfilePresenter(view, userRepository)
}