package br.com.projeto.pets.features.pet

import br.com.projeto.pets.features.ad.Ad

interface PetContract {
    interface View {
        fun success(ad: Ad)
        fun error(throwable: Throwable)
    }

    interface Presenter {
        fun onStart(petId: Int)
    }
}
