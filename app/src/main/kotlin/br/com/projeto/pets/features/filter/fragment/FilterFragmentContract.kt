package br.com.projeto.pets.features.filter.fragment

import br.com.projeto.pets.features.ad.Breed

interface FilterFragmentContract {
    interface View

    interface Presenter{
        val breedList:List<Breed>
        fun attachView(view: View)
        fun breedNameById(breedId:Int?):String?
    }
}