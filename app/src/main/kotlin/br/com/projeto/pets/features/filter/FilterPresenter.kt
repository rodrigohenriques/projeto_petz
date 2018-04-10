package br.com.projeto.pets.features.filter

import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.pet.FilterContract
import javax.inject.Inject
import javax.inject.Singleton

class FilterPresenter @Inject constructor(
        private val userRepository: UserRepository
) : FilterContract.Presenter {
    override fun onCreate() {
    }

    override fun onDestroy() {
    }

}