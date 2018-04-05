package br.com.projeto.pets.features.filter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.projeto.pets.R
import br.com.projeto.pets.features.pet.FilterContract
import dagger.android.support.DaggerAppCompatActivity

class FilterActivity : DaggerAppCompatActivity() , FilterContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, FilterActivity::class.java)
    }
}
