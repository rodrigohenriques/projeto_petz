package br.com.projeto.pets.features.filter.fragment

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import br.com.projeto.pets.R
import br.com.projeto.pets.features.main.ad.AdType
import br.com.projeto.pets.features.main.ad.QueryParams
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_filter_adpotion.*
import kotlinx.android.synthetic.main.fragment_filter_adpotion.view.*
import javax.inject.Inject

class AdoptionFilterFragment : DaggerFragment(), FilterFragmentContract.View {

    @Inject
    lateinit var presenter: FilterFragmentContract.Presenter

    private var queryParams: QueryParams = QueryParams()

    private lateinit var fragmentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        queryParams = presenter.getQueryParams(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        fragmentView = inflater.inflate(R.layout.fragment_filter_adpotion,
                container, false)
        configureView(fragmentView)
        populateFilter(fragmentView, queryParams)
        return fragmentView
    }

    override fun configureView(view: View) {
        view.breed.addItems(presenter.breedList)
        view.breed.setOnItemSelectedListener { item, _ ->
            presenter.setQueryParams(breedId = item.id)
        }

        view.filter_button.setOnClickListener {
            presenter.setQueryParams(adType = AdType.ADOPTION)
            presenter.setQueryParams(locale = view.locale.text.toString())
            activity!!.intent.putExtra(QUERY_PARAMS, presenter.getQueryParams())
            activity!!.setResult(RESULT_OK, activity!!.intent)
            activity!!.finish()
        }

        view.radioGroup.setOnCheckedChangeListener { _, id ->
            val ageClassification =  when(id) {
                R.id.checkbox_puppy -> 1
                R.id.checkbox_adult -> 2
                else  -> 3
            }
            presenter.setQueryParams(ageClassificationId = ageClassification)
        }
    }

    override fun setViewBreed(view: View, name: String?) {
        if (!name.isNullOrEmpty())
            view.breed.apply { setText(name) }
    }

    override fun setLocale(view: View, locale: String?) {
        if (!locale.isNullOrEmpty())
            view.locale.setText(locale)
    }

    override fun populateFilter(view: View, queryParams: QueryParams?) {
        if (queryParams == null)
            return

        setViewBreed(view, presenter.breedNameById(queryParams.breedId))
        setLocale(view, presenter.getQueryParams().locale)

        when (queryParams.ageClassificationId) {
            1 -> {
                fragmentView.checkbox_puppy.isChecked = true
            }
            2 -> {
                fragmentView.checkbox_adult.isChecked = true
            }
            3 -> {
                fragmentView.checkbox_aged.isChecked = true
            }
        }
    }

    companion object {
        private const val QUERY_PARAMS = "QUERY_PARAMS"

        fun newInstance(data: QueryParams? = null): AdoptionFilterFragment {
            val fragment = AdoptionFilterFragment()
            fragment.arguments = Bundle().apply {
                putSerializable(QUERY_PARAMS, data)
            }
            return fragment
        }
    }
}
