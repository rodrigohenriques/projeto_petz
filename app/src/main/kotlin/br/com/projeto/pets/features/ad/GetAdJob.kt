package br.com.projeto.pets.features.ad

import br.com.projeto.pets.data.repository.AdRepository
import br.com.projeto.pets.infra.Job
import br.com.projeto.pets.infra.Store
import io.reactivex.Completable
import timber.log.Timber

class GetAdJob constructor(
        private val store: Store<AdState>,
        private val adRepository: AdRepository
) : Job<Unit> {

    override fun bind(input: Unit, queryParams: QueryParams?): Completable {
        return adRepository.getAds(queryParams)
                .doOnError { Timber.e(it) }
                .doOnSuccess { store.update { addState(it) } }
                .toCompletable()
    }
}
