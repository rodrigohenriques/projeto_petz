package br.com.projeto.pets.features.filter.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.ad.Breed
import br.com.projeto.pets.features.pet.FilterContract
import dagger.android.support.DaggerFragment
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_filter_adpotion.*
import kotlinx.android.synthetic.main.fragment_filter_adpotion.view.*
import javax.inject.Inject

class AdoptionFilterFragment : DaggerFragment() {

    lateinit var intent: Intent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_filter_adpotion, container, false)
        intent = activity.intent

        view.breed.addItems(Paper.book().read<List<Breed>>("breed"))
        view.breed.setOnItemSelectedListener { item, _ ->
            intent.putExtra("breedId", item.id.toString())

        }

        view.filter_button.setOnClickListener {
            intent.putExtra("adType", AdType.ADOPTION.toString())
            activity.setResult(RESULT_OK, intent)
            activity.finish()
        }

        return view

    }

    companion object {
        fun newInstance(): AdoptionFilterFragment {
            val fragment = AdoptionFilterFragment()
            return fragment
        }
    }

}
