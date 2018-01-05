package br.com.projeto.pets.contracts

import br.com.projeto.pets.PresenterActivity
import io.reactivex.Observable

interface LoginContract {
  interface View {
    fun loginClick(): Observable<Pair<String, String>>

    fun showEmailFieldError()

    fun showPasswordFieldError()

    fun showErrorMessage()

    fun loginSuccess()

    fun invalidateErrors()
  }

  interface Presenter : PresenterActivity
}
