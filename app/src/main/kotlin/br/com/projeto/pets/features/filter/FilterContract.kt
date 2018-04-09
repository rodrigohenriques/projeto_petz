package br.com.projeto.pets.features.pet

import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.presenter.PresenterActivity

interface FilterContract {
    interface View

    interface Presenter : PresenterActivity {
        fun setType(adType: AdType?)
        fun setBreedId(breedId: Int)
        fun getType():String
        fun getBreedId():String
    }
}