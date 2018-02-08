package br.com.projeto.pets.features.ad

import java.util.Collections

data class AdState(
    val ads: Collection<Ad> = Collections.emptyList()
) {

  fun addState(newAds: Collection<Ad>): AdState {
    val list = ArrayList<Ad>()
    list.addAll(ads)
    list.addAll(newAds)

    return copy(ads = list)
  }
}
