package br.com.projeto.pets.features.perfil

import br.com.projeto.pets.features.create.CreateApi
import br.com.projeto.pets.features.create.CreateContract
import javax.inject.Inject

class PerfilPresenter @Inject constructor(val view: PerfilContract.View) : PerfilContract.Presenter