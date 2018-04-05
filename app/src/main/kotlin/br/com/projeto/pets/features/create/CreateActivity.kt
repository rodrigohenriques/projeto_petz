package br.com.projeto.pets.features.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.projeto.pets.R
import br.com.projeto.pets.features.pet.PetContract
import dagger.android.support.DaggerAppCompatActivity

class CreateActivity : DaggerAppCompatActivity(), PetContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, CreateActivity::class.java)
    }
}
