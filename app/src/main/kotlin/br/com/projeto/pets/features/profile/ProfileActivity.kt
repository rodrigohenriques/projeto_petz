package br.com.projeto.pets.features.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import br.com.projeto.pets.R
import br.com.projeto.pets.data.entity.User
import br.com.projeto.pets.util.Mask
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.card_informations.*
import javax.inject.Inject

class ProfileActivity : DaggerAppCompatActivity(), ProfileContract.View {

    @Inject lateinit var presenter: ProfileContract.Presenter

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, ProfileActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        presenter.onCreate()
        phoneInput.addTextChangedListener(Mask.insert("(##)#####-####", phoneInput))

        btnSave.setOnClickListener {
            presenter.updateUser(Mask.unmask(phoneInput.text.toString()),
                    cityInput.text.toString(), stateInput.text.toString())
        }
    }

    override fun userData(user: User) {
        hideLoading()
        collapsing.title = user.name
        email.text = user.email
        phoneInput.setText(user.phone)
        phoneInput.setSelection(phoneInput.text.length)
        cityInput.setText(user.city)
        cityInput.setSelection(cityInput.text.length)
        stateInput.setText(user.state)
        stateInput.setSelection(stateInput.text.length)
    }

    override fun userUpdated() {
        finish()
    }

    override fun showLoading() {
        loadingBackground.visibility = View.VISIBLE
    }

    override fun error(message: Int) {
        hideLoading()
        Snackbar.make(email, message, Snackbar.LENGTH_SHORT)
    }

    private fun hideLoading() {
        loadingBackground.visibility = View.INVISIBLE
    }

}