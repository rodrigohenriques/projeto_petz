package br.com.projeto.pets.features.filter.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.ad.Breed
import br.com.projeto.pets.features.ad.QueryParams
import dagger.android.support.DaggerFragment
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_filter_sale.view.*

class SaleFilterFragment : DaggerFragment() {

    var queryParams: QueryParams? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_filter_sale, container, false)
        configureView(view)

        return view
    }

    private fun configureView(view: View) {
        view.breed.addItems(Paper.book().read<List<Breed>>("breed"))
        view.breed.setOnItemSelectedListener { item, _ ->
            queryParams!!.breedId = item.id.toString()
        }

        view.filter_button.setOnClickListener {
            queryParams!!.breedId = AdType.SELL.toString()
            if (view.indicatorSeekBar.progress > 0) queryParams!!.breedId = view.indicatorSeekBar.progress.toString()
            activity.intent.putExtra("QUERY_PARAMS", queryParams)
            activity.setResult(Activity.RESULT_OK, activity.intent)
            activity.finish()
        }
    }


    companion object {
        private const val QUERY_PARAMS = "QUERY_PARAMS"

        fun newInstance(data: QueryParams? = null): SaleFilterFragment {
            val fragment = SaleFilterFragment()
            fragment.arguments = Bundle().apply {
                putSerializable(QUERY_PARAMS, data)
            }

            return fragment
        }
    }
}