package br.com.projeto.pets.features.filter.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.projeto.pets.R
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.ad.Breed
import com.warkiz.widget.IndicatorSeekBar
import dagger.android.support.DaggerFragment
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_filter_sale.*
import kotlinx.android.synthetic.main.fragment_filter_sale.view.*

class SaleFilterFragment : DaggerFragment() {

    lateinit var intent: Intent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_filter_sale, container, false)
        intent = activity.intent

        view.breed.addItems(Paper.book().read<List<Breed>>("breed"))
        view.breed.setOnItemSelectedListener { item, _ ->
            intent.putExtra("breedId", item.id.toString())

        }

        view.filter_button.setOnClickListener {
            intent.putExtra("adType", AdType.SELL.toString())
            if (view.indicatorSeekBar.progress > 0) intent.putExtra("ageClassificationId", view.indicatorSeekBar.progress.toString())
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
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