package br.com.projeto.pets.presenters

import br.com.projeto.pets.SignUpStateManager
import br.com.projeto.pets.contracts.SignUpContract
import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.extension.plusAssign
import io.reactivex.disposables.CompositeDisposable
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
        .doOnNext {
          stateManager.updateData(it)
          stateManager.setLoading(true)
        }
        .subscribe()
  }
}
