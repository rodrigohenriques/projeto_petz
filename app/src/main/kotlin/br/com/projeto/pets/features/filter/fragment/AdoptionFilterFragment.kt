package br.com.projeto.pets.features.filter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.projeto.pets.R
import dagger.android.support.DaggerFragment

@Suppress("UNREACHABLE_CODE")
class AdoptionFilterFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_filter_adpotion, container, false)

    }


    companion object {
        fun newInstance(): AdoptionFilterFragment {
            val fragment = AdoptionFilterFragment()
            return fragment
        }
    }
}
