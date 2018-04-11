package br.com.projeto.pets.features.filter.fragment

import br.com.projeto.pets.features.ad.Breed
import io.paperdb.Paper


class FilterFragmentPresenter(override val breedList: List<Breed> = Paper.book().read<List<Breed>>("breed")) : FilterFragmentContract.Presenter {

    lateinit var view: FilterFragmentContract.View

    override fun attachView(view: FilterFragmentContract.View) {
        this.view = view
    }

    override fun breedNameById(breedId: Int?): String? = breedList.filter { it.id == breedId }.firstOrNull()?.name
}