package br.com.projeto.pets.features.pet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.projeto.pets.R
import dagger.android.support.DaggerAppCompatActivity

class PetActivity : DaggerAppCompatActivity(), PetContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, PetActivity::class.java)
    }
}
