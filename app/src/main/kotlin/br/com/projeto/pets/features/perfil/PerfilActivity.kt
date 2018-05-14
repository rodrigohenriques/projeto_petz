package br.com.projeto.pets.features.perfil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.projeto.pets.R
import br.com.projeto.pets.features.create.CreateContract
import dagger.android.support.DaggerAppCompatActivity

class PerfilActivity : DaggerAppCompatActivity(), PerfilContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, PerfilActivity::class.java)
    }
}