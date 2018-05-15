package br.com.projeto.pets.features.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.projeto.pets.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_create.*


class CreateActivity : DaggerAppCompatActivity(), CreateContract.View {

    val PICK_IMAGE: Int = 707

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        add_photo.setOnClickListener {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, CreateActivity::class.java)
    }
}
