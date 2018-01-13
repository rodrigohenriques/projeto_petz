package br.com.projeto.pets.ui.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.EditText
import br.com.projeto.pets.R
import br.com.projeto.pets.contract.SignUpContract
import br.com.projeto.pets.contract.SignUpContract.Error.EMAIL_CONFIRMATION_MISMATCH
import br.com.projeto.pets.contract.SignUpContract.Error.EMPTY_EMAIL
import br.com.projeto.pets.contract.SignUpContract.Error.EMPTY_EMAIL_CONFIRMATION
import br.com.projeto.pets.contract.SignUpContract.Error.EMPTY_NAME
import br.com.projeto.pets.contract.SignUpContract.Error.EMPTY_PASSWORD
import br.com.projeto.pets.contract.SignUpContract.Error.INVALID_EMAIL
import br.com.projeto.pets.contract.SignUpContract.Error.INVALID_EMAIL_CONFIRMATION
import br.com.projeto.pets.contract.SignUpContract.Error.INVALID_PASSWORD
import br.com.projeto.pets.extension.plusAssign
import br.com.projeto.pets.state.signup.SignUpState
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
import kotlinx.android.synthetic.main.activity_sign_up.progressBar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SignUpActivity : DaggerAppCompatActivity(), SignUpContract.View {
  @Inject
  lateinit var presenter: SignUpContract.Presenter

  @Inject
  lateinit var stateChanges: Observable<SignUpState>

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_up)

    presenter.onCreate()

    observeStateChanges()
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.onDestroy()
  }

  override fun createAccountClicks(): Observable<SignUpContract.Data> =
      RxView.clicks(buttonCreateAccount)
          .debounce(500, TimeUnit.MILLISECONDS)
          .map { createForm() }
          .observeOn(AndroidSchedulers.mainThread())

  override fun startSession() {
    startActivity(LoggedInActivity.getCallingIntent(this))
  }

  private fun createForm(): SignUpContract.Data {
    return SignUpContract.Data(
        name = editTextUsername.text.toString(),
        password = editTextPassword.text.toString(),
        email = editTextEmail.text.toString(),
        emailConfirmation = editTextConfirmEmail.text.toString()
    )
  }

  private fun observeStateChanges() {
    val publishedStateChanges = stateChanges.observeOn(AndroidSchedulers.mainThread()).publish()
    val dataChanges = publishedStateChanges.map { it.data }.distinctUntilChanged()
    val errorChanges = publishedStateChanges.map { it.errors }.distinctUntilChanged()
    val loadingChanges = publishedStateChanges.map { it.loading }.distinctUntilChanged()

    dataChanges.doOnNext { bindData(it) }.subscribe()
    errorChanges.doOnNext { bindErrors(it) }.subscribe()
    loadingChanges.doOnNext { bindLoading(it) }.subscribe()

    disposables += publishedStateChanges.connect()
  }

  private fun bindData(data: SignUpContract.Data) {
    editTextUsername.setText(data.name)
    editTextPassword.setText(data.password)
    editTextEmail.setText(data.email)
    editTextConfirmEmail.setText(data.emailConfirmation)
  }

  private fun bindErrors(errors: Set<SignUpContract.Error>) {
    invalidateErrors()
    errors.forEach { handleError(it) }
  }

  private fun bindLoading(loading: Boolean) {
    progressBar.visibility = if (loading) View.VISIBLE else View.GONE
  }

  private fun invalidateErrors() {
    editTextUsername.error = null
    editTextPassword.error = null
    editTextEmail.error = null
    editTextConfirmEmail.error = null
  }

  private fun handleError(error: SignUpContract.Error) {
    when (error) {
      EMPTY_NAME -> editTextUsername.bind(error)
      EMPTY_PASSWORD, INVALID_PASSWORD -> editTextPassword.bind(error)
      EMPTY_EMAIL, INVALID_EMAIL -> editTextEmail.bind(error)
      EMPTY_EMAIL_CONFIRMATION,
      INVALID_EMAIL_CONFIRMATION,
      EMAIL_CONFIRMATION_MISMATCH -> editTextConfirmEmail.bind(error)
      else -> error.show()
    }
  }

  private fun EditText.bind(signUpError: SignUpContract.Error) {
    error = signUpError.getMessage(context)
  }

  private fun SignUpContract.Error.show() {
    val message = getMessage(this@SignUpActivity)
    val snackbar = Snackbar.make(buttonCreateAccount, message, Snackbar.LENGTH_LONG)
    snackbar.setAction(R.string.action_got_it) { snackbar.dismiss() }
    snackbar.show()
  }
}
