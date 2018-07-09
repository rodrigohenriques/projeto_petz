package br.com.projeto.pets.data.repository

import br.com.projeto.pets.data.api.AdApi
import br.com.projeto.pets.data.entity.Ad
import br.com.projeto.pets.features.ad.QueryParams
import io.reactivex.Single
import javax.inject.Inject


class AdRepository @Inject constructor(private val adApi: AdApi) {
  fun getAds(queryParams: QueryParams?): Single<List<Ad>> =
          adApi.getAllAd(queryParams?.breedId,queryParams?.ageClassificationId,queryParams?.locale)
}
