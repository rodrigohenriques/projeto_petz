package br.com.projeto.pets.features.main.ad

import br.com.projeto.pets.extension.plusAssign
import br.com.projeto.pets.infra.Job
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class AdHub constructor(
        private val getAdJob: Job<Unit>
) : AdContract.Hub {

    private val disposable = CompositeDisposable()

    override fun connect(queryParams: QueryParams?) {
        if (disposable.size() > 0 ) {
            return
        }

        disposable += getAdJob.bind(Unit, queryParams)
                .doOnError { Timber.e(it) }
                .onErrorComplete()
                .subscribe()
    }

    override fun disconnect() {
        disposable.clear()
    }
}
