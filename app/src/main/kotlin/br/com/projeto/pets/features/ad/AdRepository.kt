package br.com.projeto.pets.features.ad

import io.reactivex.Single
import javax.inject.Inject


class AdRepository @Inject constructor(private val adApi: AdApi) {
  fun getAds(): Single<Result<Ad>> = adApi.getAllAd()
}
data class Result<out T>(val result: Collection<T>)