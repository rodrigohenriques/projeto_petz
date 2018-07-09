package br.com.projeto.pets.features.filter

import br.com.projeto.pets.data.repository.UserRepository
import javax.inject.Inject

class FilterPresenter @Inject constructor(
        private val userRepository: UserRepository
) : FilterContract.Presenter {
    override fun onCreate() {
    }

    override fun onDestroy() {
    }

}