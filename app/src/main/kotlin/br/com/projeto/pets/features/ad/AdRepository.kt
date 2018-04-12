package br.com.projeto.pets.features.ad

import io.reactivex.Single
import javax.inject.Inject


class AdRepository @Inject constructor(private val adApi: AdApi) {
  fun getAds(queryParams: QueryParams?): Single<List<Ad>> = adApi.getAllAd(queryParams?.breedId,queryParams?.ageClassificationId,queryParams?.locale)
}

data class Result<out T>(val result: T)