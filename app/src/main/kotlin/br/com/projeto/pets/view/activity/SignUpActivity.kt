package br.com.projeto.pets.view.activity

import android.os.Bundle
import br.com.projeto.pets.R
import br.com.projeto.pets.StateManager
import br.com.projeto.pets.contracts.SignUpContract
import br.com.projeto.pets.data.entities.CreateUser
import br.com.projeto.pets.states.SignUpState
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SignUpActivity : DaggerAppCompatActivity(), SignUpContract.View {
  @Inject
  lateinit var presenter: SignUpContract.Presenter

  @Inject
  lateinit var stateManager: StateManager<SignUpState>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_up)

    stateManager.stateChanges()
        .observeOn(AndroidSchedulers.mainThread())
        .distinctUntilChanged()
        .subscribe { bindState(it) }
  }

  override fun createAccountClicks(): Observable<SignUpContract.Form> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  private fun bindState(state: SignUpState) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
