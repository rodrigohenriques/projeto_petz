package br.com.projeto.pets.features.filter.fragment

import android.os.Bundle
import br.com.projeto.pets.features.main.ad.AdType
import br.com.projeto.pets.data.entity.Breed
import br.com.projeto.pets.features.main.ad.QueryParams
import io.paperdb.Paper


class FilterFragmentPresenter(override val breedList: List<Breed> = Paper.book().read("breed")) :
        FilterFragmentContract.Presenter {

    private val QUERY_PARAMS = "QUERY_PARAMS"
    lateinit var view: FilterFragmentContract.View
    private var queryParams: QueryParams = QueryParams()

    override fun attachView(view: FilterFragmentContract.View) {
        this.view = view
    }

    override fun breedNameById(breedId: Int?): String? =
            breedList.firstOrNull { it.id == breedId }?.name

    override fun getQueryParams(arguments: Bundle?): QueryParams {
        if (arguments?.getSerializable(QUERY_PARAMS) != null) {
            queryParams = arguments.getSerializable(QUERY_PARAMS) as QueryParams
        }
        return queryParams
    }

    override fun setQueryParams(adType: AdType?, breedId: Int?,
                                ageClassificationId: Int?, locale: String?) {
        if (adType != null) queryParams.adType = adType.toString()
        if (breedId != null) queryParams.breedId = breedId
        if (ageClassificationId != null) queryParams.ageClassificationId = ageClassificationId
        if (!locale.isNullOrEmpty()) queryParams.locale = locale
    }
}
