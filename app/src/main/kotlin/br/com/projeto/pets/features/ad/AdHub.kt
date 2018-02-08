package br.com.projeto.pets.features.ad

import br.com.projeto.pets.extension.plusAssign
import br.com.projeto.pets.infra.Job
import io.reactivex.disposables.CompositeDisposable

class AdHub constructor(
    private val getAdJob: Job<Unit>
) : AdContract.Hub {

  private val disposable = CompositeDisposable()

  override fun connect() {
    disposable += getAdJob.bind(Unit)
        .onErrorComplete()
        .subscribe()
  }

  override fun disconnect() {
    disposable.clear()
  }
}
