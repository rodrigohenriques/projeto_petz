package br.com.projeto.pets.features.pet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.projeto.pets.R
import br.com.projeto.pets.util.Spinner
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_pet.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_filter_adpotion.*


class PetActivity : DaggerAppCompatActivity(), PetContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_filter_adpotion)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        var strings: ArrayList<String> = ArrayList()
        strings.add("item 1")
        strings.add("item 2")
        strings.add("item 3")


        signup_text_input_job_category.addItems(strings)
        signup_text_input_job_category.setOnItemSelectedListener { item, _ -> callTOAS(item) }
    }

    private fun callTOAS(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, PetActivity::class.java)
    }
}
