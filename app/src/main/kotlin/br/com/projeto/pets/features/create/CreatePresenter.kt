package br.com.projeto.pets.features.create

import javax.inject.Inject

class CreatePresenter @Inject constructor(val view: CreateContract.View, val createApi: CreateApi) : CreateContract.Presenter