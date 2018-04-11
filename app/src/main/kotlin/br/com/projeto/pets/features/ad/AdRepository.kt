package br.com.projeto.pets.features.ad

import io.reactivex.Single
import javax.inject.Inject


class AdRepository @Inject constructor(private val adApi: AdApi) {
  fun getAds(breedId: Int? = null,
             ageClassificationId: Int? = null): Single<List<Ad>> = adApi.getAllAd(breedId,ageClassificationId)
}

data class Result<out T>(val result: T)