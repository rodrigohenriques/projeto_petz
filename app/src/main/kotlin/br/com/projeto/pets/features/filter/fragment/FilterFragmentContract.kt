package br.com.projeto.pets.features.filter.fragment

import android.os.Bundle
import br.com.projeto.pets.features.main.ad.AdType
import br.com.projeto.pets.data.entity.Breed
import br.com.projeto.pets.features.main.ad.QueryParams

interface FilterFragmentContract {
    interface View {
        fun configureView(view: android.view.View)
        fun populateFilter(view: android.view.View, queryParams: QueryParams?)
        fun setLocale(view: android.view.View, locale: String?)
        fun setViewBreed(view: android.view.View, name: String?)
    }

    interface Presenter {
        val breedList: List<Breed>
        fun attachView(view: View)
        fun breedNameById(breedId: Int?): String?
        fun getQueryParams(arguments: Bundle? = null): QueryParams
        fun setQueryParams(adType: AdType? = null, breedId: Int? = null,
                           ageClassificationId: Int? = null,
                           age: Int? = null,
                           locale: String? = null)
    }
}