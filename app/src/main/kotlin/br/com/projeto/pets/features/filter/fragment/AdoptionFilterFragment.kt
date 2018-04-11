package br.com.projeto.pets.features.filter.fragment

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.ad.Breed
import br.com.projeto.pets.features.ad.QueryParams
import dagger.android.support.DaggerFragment
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_filter_adpotion.view.*

class AdoptionFilterFragment : DaggerFragment() {

    private var queryParams: QueryParams = QueryParams()
    private lateinit var breedList: List<Breed>
    private val QUERY_PARAMS = "QUERY_PARAMS"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments.getSerializable(QUERY_PARAMS) != null) {
            queryParams = arguments.getSerializable(QUERY_PARAMS) as QueryParams
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_filter_adpotion, container, false)
        breedList = Paper.book().read<List<Breed>>("breed")
        viewConfiguration(view)
        populateFilter(view, queryParams)
        return view

    }

    fun viewConfiguration(view: View) {
        view.breed.addItems(breedList)
        view.breed.setOnItemSelectedListener { item, _ ->
            queryParams.breedId = item.id

        }

        view.checkbox_puppy.setOnClickListener { checkOnlyOneCheckBox(it as CheckBox, 1) }
        view.checkbox_adult.setOnClickListener { checkOnlyOneCheckBox(it as CheckBox, 2) }
        view.checkbox_aged.setOnClickListener { checkOnlyOneCheckBox(it as CheckBox, 3) }

        view.filter_button.setOnClickListener {
            queryParams.adType = AdType.ADOPTION.toString()
            activity.intent.putExtra(QUERY_PARAMS, queryParams)
            activity.setResult(RESULT_OK, activity.intent)
            activity.finish()
        }
    }

    fun populateFilter(view: View, queryParams: QueryParams?) {
        if (queryParams == null || queryParams.adType != AdType.ADOPTION.toString())
            return
        view.breed.apply {
            breedList.filter { it.id == queryParams.breedId }
                    .firstOrNull()?.name
                    .let { s ->
                        s.isNullOrEmpty()
                                .let { if (!it) setText(s) }
                    }
        }
        when (queryParams.ageClassificationId) {
            1 -> {
                view.checkbox_puppy.isChecked = true
            }
            2 -> {
                view.checkbox_adult.isChecked = true
            }
            3 -> {
                view.checkbox_aged.isChecked = true
            }
        }
    }

    fun checkOnlyOneCheckBox(checkBoxView: CheckBox, position: Int) {
        val ageClassificationId: Int? = when (checkBoxView.isChecked) {
            true -> position
            else -> null
        }
        queryParams.ageClassificationId = ageClassificationId
        when (position) {
            1 -> {
                view!!.checkbox_adult.isChecked = false
                view!!.checkbox_aged.isChecked = false
            }
            2 -> {
                view!!.checkbox_puppy.isChecked = false
                view!!.checkbox_aged.isChecked = false
            }
            else -> {
                view!!.checkbox_puppy.isChecked = false
                view!!.checkbox_adult.isChecked = false
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
