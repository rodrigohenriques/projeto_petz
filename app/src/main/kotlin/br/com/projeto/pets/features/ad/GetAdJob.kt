package br.com.projeto.pets.features.ad

import br.com.projeto.pets.infra.Job
import br.com.projeto.pets.infra.Store
import io.reactivex.Completable
import timber.log.Timber

class GetAdJob constructor(
        private val store: Store<AdState>,
        private val adRepository: AdRepository
) : Job<Unit> {

    override fun bind(input: Unit, breedId: String?, ageClassificationId: String?): Completable {
        return adRepository.getAds(breedId, ageClassificationId)
                .doOnError { Timber.e(it) }
                .doOnSuccess { store.update { addState(it) } }
                .toCompletable()
    }

//  override fun bind(input: Unit,filter: Filter): Completable {
//    return adRepository.getAds()
//            .doOnError { Timber.e(it) }
//            .doOnSuccess { store.update { addState(it) } }
//            .toCompletable()
//  }
}
