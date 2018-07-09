package br.com.projeto.pets.features.init.signup

import br.com.projeto.pets.data.entity.NewUser
import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.extension.plusAssign
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class SignUpPresenter @Inject constructor(
        private val view: SignUpContract.View,
        private val userRepository: UserRepository,
        private val stateManager: SignUpStateManager
) : SignUpContract.Presenter {

  private val compositeDisposable = CompositeDisposable()

  override fun onCreate() {
    observeViewEvents()
  }

  override fun onDestroy() {
    compositeDisposable.clear()
  }

  private fun observeViewEvents() {
    compositeDisposable += view.createAccountClicks()
        .doOnNext { stateManager.updateData(it) }
        .filter { stateManager.state().hasNoErrors() }
        .map { NewUser(it.name!!, it.email!!, it.password!!) }
        .doOnNext { stateManager.setLoading(true) }
        .flatMapCompletable { createUser(it) }
        .retry()
        .subscribe()
  }

  private fun createUser(it: NewUser): Completable? {
    return userRepository.createUser(it)
        .doOnEvent { stateManager.setLoading(false) }
        .observeOn(AndroidSchedulers.mainThread())
        .doOnComplete { view.startSession() }
        .doOnError { stateManager.setError(it) }
        .doOnError { Timber.e(it) }
        .onErrorComplete()
  }
}
