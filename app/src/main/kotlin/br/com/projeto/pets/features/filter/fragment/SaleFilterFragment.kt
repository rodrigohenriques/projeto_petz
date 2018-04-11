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

    private val breedList: List<Breed> = Paper.book().read<List<Breed>>("breed")
    var queryParams: QueryParams? = QueryParams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        queryParams = arguments.getSerializable(QUERY_PARAMS) as QueryParams?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_filter_sale, container, false)
        populateFilter(queryParams)
        configureView(view)

        return view
    }

    private fun configureView(view: View) {
        view.breed.addItems(breedList)
        view.breed.setOnItemSelectedListener { item, _ ->
            queryParams?.breedId = item.id
        }

        view.filter_button.setOnClickListener {
            queryParams?.adType = AdType.SELL.toString()
            if (view.indicatorSeekBar.progress > 0) queryParams?.ageClassificationId = view.indicatorSeekBar.progress
            activity.intent.putExtra("QUERY_PARAMS", queryParams)
            activity.setResult(Activity.RESULT_OK, activity.intent)
            activity.finish()
        }
    }

    fun populateFilter(view: View, queryParams: QueryParams?) {
        if (queryParams == null)
            return
        view.breed.apply {
            breedList.filter { it.id == queryParams.breedId }
                    .firstOrNull()?.name
                    .let { s ->
                        s.isNullOrEmpty()
                                .let { if (!it) setText(s) }
                    }
        }
        view.indicatorSeekBar.setProgress(queryParams.ageClassificationId as Float);
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