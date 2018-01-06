package br.com.projeto.pets.view.activity

import android.os.Bundle
import br.com.projeto.pets.R
import br.com.projeto.pets.SignUpStateManager
import br.com.projeto.pets.contracts.SignUpContract
import br.com.projeto.pets.contracts.SignUpContract.Error.EMAIL_CONFIRMATION_MISMATCH
import br.com.projeto.pets.contracts.SignUpContract.Error.EMPTY_EMAIL
import br.com.projeto.pets.contracts.SignUpContract.Error.EMPTY_EMAIL_CONFIRMATION
import br.com.projeto.pets.contracts.SignUpContract.Error.EMPTY_PASSWORD
import br.com.projeto.pets.contracts.SignUpContract.Error.EMPTY_USERNAME
import br.com.projeto.pets.contracts.SignUpContract.Error.INVALID_EMAIL
import br.com.projeto.pets.contracts.SignUpContract.Error.INVALID_EMAIL_CONFIRMATION
import br.com.projeto.pets.contracts.SignUpContract.Error.INVALID_PASSWORD
import br.com.projeto.pets.contracts.SignUpContract.Error.INVALID_USERNAME
import br.com.projeto.pets.extension.plusAssign
import br.com.projeto.pets.states.SignUpState
import com.jakewharton.rxbinding2.view.RxView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_sign_up.buttonCreateAccount
import kotlinx.android.synthetic.main.activity_sign_up.editTextConfirmEmail
import kotlinx.android.synthetic.main.activity_sign_up.editTextEmail
import kotlinx.android.synthetic.main.activity_sign_up.editTextPassword
import kotlinx.android.synthetic.main.activity_sign_up.editTextUsername
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SignUpActivity : DaggerAppCompatActivity(), SignUpContract.View {
  @Inject
  lateinit var presenter: SignUpContract.Presenter

  @Inject
  lateinit var stateManager: SignUpStateManager

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_up)

    presenter.onCreate()

    disposables += stateManager.stateChanges()
        .observeOn(AndroidSchedulers.mainThread())
        .distinctUntilChanged()
        .doOnNext { bindState(it) }
        .retry(3)
        .subscribe()
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.onDestroy()
  }

  override fun createAccountClicks(): Observable<SignUpContract.Form> =
      RxView.clicks(buttonCreateAccount)
          .debounce(500, TimeUnit.MILLISECONDS)
          .map { createForm() }
          .distinctUntilChanged()
          .observeOn(AndroidSchedulers.mainThread())

  private fun createForm(): SignUpContract.Form {
    return SignUpContract.Form(
        username = editTextUsername.text.toString(),
        password = editTextPassword.text.toString(),
        email = editTextEmail.text.toString(),
        emailConfirmation = editTextConfirmEmail.text.toString()
    )
  }

  private fun bindState(state: SignUpState) {
    bindForm(state.data)
    bindErrors(state.errors)
  }

  private fun bindForm(form: SignUpContract.Form) {
    editTextUsername.setText(form.username)
    editTextPassword.setText(form.password)
    editTextEmail.setText(form.email)
    editTextConfirmEmail.setText(form.emailConfirmation)
  }

  private fun bindErrors(errors: Set<SignUpContract.Error>) {
    invalidateErrors()
    errors.forEach { errorActions[it]?.invoke() }
  }

  private fun invalidateErrors() {
    editTextUsername.error = null
    editTextPassword.error = null
    editTextEmail.error = null
    editTextConfirmEmail.error = null
  }

  private val errorActions = mapOf(
      EMPTY_USERNAME to { editTextUsername.error = getString(R.string.error_required_field) },
      EMPTY_PASSWORD to { editTextPassword.error = getString(R.string.error_required_field) },
      EMPTY_EMAIL to { editTextEmail.error = getString(R.string.error_required_field) },
      EMPTY_EMAIL_CONFIRMATION to { editTextConfirmEmail.error = getString(R.string.error_required_field) },
      INVALID_USERNAME to { editTextUsername.error = getString(R.string.error_invalid_username) },
      INVALID_PASSWORD to { editTextPassword.error = getString(R.string.error_invalid_password) },
      INVALID_EMAIL to { editTextEmail.error = getString(R.string.error_invalid_email) },
      INVALID_EMAIL_CONFIRMATION to { editTextConfirmEmail.error = getString(R.string.error_invalid_email) },
      EMAIL_CONFIRMATION_MISMATCH to { editTextConfirmEmail.error = getString(R.string.error_email_confirmation_mismatch) }
  )
}
