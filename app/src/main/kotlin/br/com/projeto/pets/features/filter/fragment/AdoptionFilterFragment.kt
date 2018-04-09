package br.com.projeto.pets.features.filter.fragment

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
import kotlinx.android.synthetic.main.fragment_filter_adpotion.view.*
import javax.inject.Inject

class AdoptionFilterFragment : DaggerFragment() {

    @Inject
    lateinit var presenter: FilterContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_filter_adpotion, container, false)

        view.breed.addItems(Paper.book().read<List<Breed>>("breed"))
        view.breed.setOnItemSelectedListener { item, _ -> presenter.setBreedId(item.id) }

        view.filter_button.setOnClickListener {
            presenter.setType(AdType.ADOPTION)
            activity.finish()
        }

        return view

    }


    private fun callTOAS(s: String) {
        Toast.makeText(this.activity, s, Toast.LENGTH_LONG).show()
    }


    companion object {
        fun newInstance(): AdoptionFilterFragment {
            val fragment = AdoptionFilterFragment()
            return fragment
        }
    }

}
