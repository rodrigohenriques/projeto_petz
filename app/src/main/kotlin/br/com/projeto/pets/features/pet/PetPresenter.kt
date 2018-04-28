package br.com.projeto.pets.features.pet

import javax.inject.Inject

class PetPresenter @Inject constructor(
        private val view: PetContract.View,
        private val petApi: PetApi
) : PetContract.Presenter {


}