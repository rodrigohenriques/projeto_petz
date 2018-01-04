package br.com.projeto.pets.view.activity

import android.os.Bundle
import android.widget.Toast
import br.com.projeto.pets.R
import br.com.projeto.pets.presenter.LoginContract
import com.jakewharton.rxbinding2.view.RxView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity(), LoginContract.View {
    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun loginClick(): Observable<Pair<String, String>> = RxView.clicks(login)
            .map { Pair(email.text.toString(), password.text.toString()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email.setText("rdiego26@gmail.com")
        password.setText("827ccb0eea8a706c4c34a16891f84e7b")

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showEmailFieldError() {
        email.error = getString(R.string.email_error)
    }

    override fun showPasswordFieldError() {
        password.error = getString(R.string.passwordRequired)
    }

    override fun showErrorMessage() {
        Toast.makeText(this, R.string.loginError, Toast.LENGTH_LONG).show()
    }

    override fun loginSuccess() {
        // TODO: ir para a proxima activity
    }

    override fun invalidateErrors() {
        email.error = null
        password.error = null
    }
}
