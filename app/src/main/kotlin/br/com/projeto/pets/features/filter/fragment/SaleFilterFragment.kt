package br.com.projeto.pets.features.filter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.Breed
import dagger.android.support.DaggerFragment
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_filter_sale.view.*

class SaleFilterFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_filter_sale, container, false)


        view.breed.addItems(Paper.book().read<List<Breed>>("breed"))
        view.breed.setOnItemSelectedListener { item, _ -> callTOAS(item.id.toString()) }


        return view

    }

    private fun callTOAS(s: String) {
        Toast.makeText(this.activity, s, Toast.LENGTH_LONG).show()
    }


    companion object {
        fun newInstance(): SaleFilterFragment {
            val fragment = SaleFilterFragment()
            return fragment
        }
    }
}