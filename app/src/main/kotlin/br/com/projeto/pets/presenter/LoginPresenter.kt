package br.com.projeto.pets.presenter

import br.com.projeto.pets.contract.LoginContract
import br.com.projeto.pets.data.entity.Credential
import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.extension.errorMessage
import br.com.projeto.pets.extension.isEmail
import br.com.projeto.pets.extension.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
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
                .flatMapCompletable {
                    userRepository.signIn(it)
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnError {
                                val errorMessage = getErrorMessage(it)

                                view.showErrorMessage(errorMessage)
                            }
                            .doOnComplete { view.loginSuccess() }
                }
                .retry()
                .subscribe()
    }

    private fun getErrorMessage(it: Throwable?): String? {
        return when (it) {
            is HttpException -> {
                it.errorMessage() ?: when (it.code()) {
                    401 -> "Dados incorretos"
                    else -> null
                }
            }
            else -> null
        }
    }

    private fun validateFields(it: Pair<String, String>): Boolean {
        var procced = true

        if (it.first.isBlank() || !it.first.isEmail()) {
            view.showEmailFieldError()
            procced = false
        }

        if (it.second.isEmpty()) {
            view.showPasswordFieldError()
            procced = false
        }

        return procced
    }
}
