package br.com.projeto.pets.contract

import br.com.projeto.pets.presenter.PresenterActivity
import io.reactivex.Observable

interface LoginContract {
  interface View {
    fun loginClick(): Observable<Pair<String, String>>

    fun showEmailFieldError()

    fun showPasswordFieldError()

    fun showErrorMessage(message: String? = null)

    fun loginSuccess()

    fun invalidateErrors()
  }

  interface Presenter : PresenterActivity
}
