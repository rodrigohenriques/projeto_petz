package br.com.projeto.pets.features.filter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.projeto.pets.R
import dagger.android.support.DaggerFragment

class SaleFilterFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_filter_sale, container, false)

    }


    companion object {
        fun newInstance(): SaleFilterFragment {
            val fragment = SaleFilterFragment()
            return fragment
        }
    }
}