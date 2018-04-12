package br.com.projeto.pets.features.filter.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.ad.QueryParams
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_filter_sale.view.*
import javax.inject.Inject

class SaleFilterFragment : DaggerFragment(), FilterFragmentContract.View {

    @Inject
    lateinit var presenter: FilterFragmentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getQueryParams(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_filter_sale, container, false)
        configureView(view)
        populateFilter(view, presenter.getQueryParams())
        return view
    }

    override fun configureView(view: View) {
        view.breed.addItems(presenter.breedList)
        view.breed.setOnItemSelectedListener { item, _ ->
            presenter.setQueryParams(breedId = item.id)
        }

        view.filter_button.setOnClickListener {
            presenter.setQueryParams(adType = AdType.SELL)
            presenter.setQueryParams(locale = view.locale.text.toString())
            if (view.indicatorSeekBar.progress > 0) presenter.setQueryParams(ageClassificationId = view.indicatorSeekBar.progress)
            activity.intent.putExtra("QUERY_PARAMS", presenter.getQueryParams())
            activity.setResult(Activity.RESULT_OK, activity.intent)
            activity.finish()
        }
    }

    override fun setViewBreed(view: View, name: String?) {
        if (!name.isNullOrEmpty())
            view.breed.apply { setText(name) }
    }

    override fun setLocale(view: View, locale: String?) {
        if (!locale.isNullOrEmpty())
            view.locale.apply { setText(locale) }
    }


    override fun populateFilter(view: View, queryParams: QueryParams?) {
        if (queryParams == null || queryParams.adType != AdType.SELL.toString())
            return

        setViewBreed(view, presenter.breedNameById(queryParams.breedId))
        setLocale(view, presenter.getQueryParams().locale)
        setAged(view, presenter.getQueryParams().ageClassificationId)

    }

    override fun setAged(view: View, age: Int?) {
        if (age != null) {
            view.indicatorSeekBar.setProgress(age.toFloat())
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