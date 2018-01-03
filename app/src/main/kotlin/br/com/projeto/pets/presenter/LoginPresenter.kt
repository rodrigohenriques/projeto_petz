package br.com.projeto.pets.presenter

import br.com.projeto.pets.data.entity.Credential
import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.extension.isEmail
import br.com.projeto.pets.extension.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginPresenter @Inject constructor(
        private val view: LoginContract.View,
        private val userRepository: UserRepository
) : LoginContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        observeViewEvents()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    private fun observeViewEvents() {
        compositeDisposable += view.loginClick()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { view.invalidateErrors() }
                .filter { validateFields(it) }
                .map { Credential(it.first, it.second) }
                .flatMapCompletable { userRepository.signIn(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { view.showErrorMessage() }
                .doOnComplete { view.loginSuccess() }
                .retry()
                .subscribe()
    }

    private fun validateFields(it: Pair<String, String>): Boolean {
        var proced = true

        if (it.first.isBlank() || !it.first.isEmail()) {
            view.showEmailFieldError()
            proced = false
        }

        if (it.second.isEmpty()) {
            view.showPasswordFieldError()
            proced = false
        }

        return proced
    }
}

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
