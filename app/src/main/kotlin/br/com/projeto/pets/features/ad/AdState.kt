package br.com.projeto.pets.features.ad

import br.com.projeto.pets.data.entity.Ad
import java.util.Collections

data class AdState(
    val ads: List<Ad> = Collections.emptyList()
) {

  fun addState(newAds: List<Ad>): AdState {
    val list = ArrayList<Ad>()
    list.addAll(ads)
    list.addAll(newAds)

    return copy(ads = list)
  }
}
