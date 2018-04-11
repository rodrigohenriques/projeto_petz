package br.com.projeto.pets.features.filter.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.ad.Breed
import dagger.android.support.DaggerFragment
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_filter_adpotion.view.*

class AdoptionFilterFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_filter_adpotion, container, false)
        viewConfiguration(view)
        return view

    }

    fun viewConfiguration(view: View) {
        view.breed.addItems(Paper.book().read<List<Breed>>("breed"))
        view.breed.setOnItemSelectedListener { item, _ ->
            activity.intent.putExtra("breedId", item.id.toString())

        }

        view.checkbox_puppy.setOnClickListener { view -> checkOnlyOneCheckBox(view as CheckBox, 1) }
        view.checkbox_adult.setOnClickListener { view -> checkOnlyOneCheckBox(view as CheckBox, 2) }
        view.checkbox_aged.setOnClickListener { view -> checkOnlyOneCheckBox(view as CheckBox, 3) }

        view.filter_button.setOnClickListener {
            activity.intent.putExtra("adType", AdType.ADOPTION.toString())
            activity.setResult(RESULT_OK, activity.intent)
            activity.finish()
        }
    }

    fun checkOnlyOneCheckBox(checkBoxView: CheckBox, position: Int) {
        val ageClassificationId: String? = when (checkBoxView.isChecked) {
            true -> position.toString()
            else -> null
        }
        activity.intent.putExtra("ageClassificationId", ageClassificationId)
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
        fun newInstance(): AdoptionFilterFragment {
            val fragment = AdoptionFilterFragment()
            return fragment
        }
    }

}
