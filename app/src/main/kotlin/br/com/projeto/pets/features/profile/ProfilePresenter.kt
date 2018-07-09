package br.com.projeto.pets.features.profile

import javax.inject.Inject

class ProfilePresenter @Inject constructor(val view: ProfileContract.View) : ProfileContract.Presenter