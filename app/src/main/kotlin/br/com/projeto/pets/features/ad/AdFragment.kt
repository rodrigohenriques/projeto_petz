package br.com.projeto.pets.features.ad

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.projeto.pets.R
import br.com.projeto.pets.extension.plusAssign
import br.com.projeto.pets.infra.Store
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_ad.*
import kotlinx.android.synthetic.main.fragment_ad.view.*
import timber.log.Timber
import java.io.Serializable
import javax.inject.Inject

class AdFragment : DaggerFragment() {

    @Inject
    lateinit var hub: AdContract.Hub

    @Inject
    lateinit var state: Store<AdState>

    private lateinit var type: AdType

    private val disposable = CompositeDisposable()

    private lateinit var adAdapter: AdAdapter

    private var queryParams: QueryParams? = QueryParams()

    private val layoutManager by lazy {
        LinearLayoutManager(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getSerializable(TYPE_ARGS) as AdType
        if(arguments!!.getSerializable(QUERY_PARAMS) != null)
            queryParams = arguments!!.getSerializable(QUERY_PARAMS) as QueryParams?
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        adAdapter = AdAdapter(context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_ad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        observeState()

        view.swipe_refresh.isEnabled = false
        view.swipe_refresh.setOnRefreshListener { updateRecycleView() }
        updateRecycleView()
    }

    private fun updateRecycleView() {
        hub.connect(queryParams!!)
    }

    private fun initView(view: View) {
        view.adList.layoutManager = layoutManager
        view.adList.adapter = adAdapter
    }

    private fun observeState() {
        disposable += state.stateChanges()
                .observeOn(AndroidSchedulers.mainThread())
                .distinctUntilChanged()
                .doOnError { Timber.e(it) }
                .subscribe { changeState(it) }
    }

    private fun changeState(adState: AdState) {
        if (adState.ads.isEmpty()) {
            swipe_refresh.isRefreshing = false
            swipe_refresh.isEnabled = false
            progressBar.visibility = View.GONE
            return
        }
        swipe_refresh.isEnabled = true
        swipe_refresh.isRefreshing = false
        progressBar.visibility = View.GONE
        adList.visibility = View.VISIBLE
        adAdapter.addAds(adState.ads)

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        hub.disconnect()
    }

    companion object {
        private const val TYPE_ARGS = "TYPE"
        private const val QUERY_PARAMS = "QUERY_PARAMS"

        fun newInstance(type: AdType, data: QueryParams? = null): AdFragment {
            val fragment = AdFragment()

            fragment.arguments = Bundle().apply {
                putSerializable(TYPE_ARGS, type)
                putSerializable(QUERY_PARAMS, data)
            }

            return fragment
        }

    }
}

enum class AdType(val type: Int) {
    SELL(R.string.sell),
    ADOPTION(R.string.adoption)
}

data class QueryParams(var adType: String? = null, var breedId: Int? = null, var ageClassificationId: Int? = null, var locale: String? = null) : Serializable