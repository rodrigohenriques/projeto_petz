package br.com.projeto.pets.features.pet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import br.com.projeto.pets.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_pet.*
import javax.inject.Inject


class PetActivity : DaggerAppCompatActivity(), PetContract.View {

    @Inject
    lateinit var presenter : PetContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, PetActivity::class.java)
    }
}
