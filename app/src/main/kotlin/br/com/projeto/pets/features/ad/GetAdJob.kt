package br.com.projeto.pets.features.ad

import br.com.projeto.pets.infra.Job
import br.com.projeto.pets.infra.Store
import io.reactivex.Completable

class GetAdJob constructor(
    private val store: Store<AdState>,
    private val adRepository: AdRepository
) : Job<Unit> {

  override fun bind(input: Unit): Completable {
    return adRepository.getAds()
        .map { it.result }
        .doOnSuccess { store.update { addState(it) } }
        .toCompletable()
  }
}
