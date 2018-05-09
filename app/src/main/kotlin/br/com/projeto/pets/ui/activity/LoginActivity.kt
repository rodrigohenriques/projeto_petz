package br.com.projeto.pets.ui.activity

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.support.design.widget.Snackbar
import br.com.projeto.pets.R
import br.com.projeto.pets.contract.LoginContract
import br.com.projeto.pets.features.base.BaseActivity
import com.jakewharton.rxbinding2.view.RxView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_login.email
import kotlinx.android.synthetic.main.activity_login.login
import kotlinx.android.synthetic.main.activity_login.password
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun loginClick(): Observable<Pair<String, String>> = RxView.clicks(login)
            .map { Pair(email.text.toString(), password.text.toString()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

    override fun showErrorMessage(message: String?) {
        val error = message ?: getString(R.string.loginError)
        val snackbar = Snackbar.make(email, error, Snackbar.LENGTH_LONG)
        snackbar.setAction(R.string.action_got_it) { snackbar.dismiss() }
        snackbar.show()
    }

    override fun loginSuccess() {
        val intent = BaseActivity.getCallingIntent(this)
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(FLAG_ACTIVITY_SINGLE_TOP)
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
        startActivity(BaseActivity.getCallingIntent(this))
        finish()
    }

    override fun invalidateErrors() {
        email.error = null
        password.error = null
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
