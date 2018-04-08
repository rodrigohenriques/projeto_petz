package br.com.projeto.pets.features.filter

import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.features.pet.FilterContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilterPresenter @Inject constructor(
        private val userRepository: UserRepository
) : FilterContract.Presenter {



    override fun onDestroy() {
    }

    override fun onCreate() {
    }


}