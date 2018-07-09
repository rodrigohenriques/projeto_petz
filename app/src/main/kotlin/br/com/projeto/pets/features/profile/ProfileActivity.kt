package br.com.projeto.pets.features.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.projeto.pets.R
import dagger.android.support.DaggerAppCompatActivity

class ProfileActivity : DaggerAppCompatActivity(), ProfileContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, ProfileActivity::class.java)
    }
}