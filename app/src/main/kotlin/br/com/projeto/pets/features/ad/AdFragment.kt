package br.com.projeto.pets.features.ad

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.projeto.pets.R
import br.com.projeto.pets.extension.plusAssign
import br.com.projeto.pets.infra.Store
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject


class AdFragment : DaggerFragment() {

  @Inject
  lateinit var hub: AdContract.Hub

  @Inject
  lateinit var state: Store<AdState>

  private lateinit var type: AdType

  private val disposable = CompositeDisposable()

  private lateinit var adAdapter: AdAdapter

  private lateinit var recyclerView: RecyclerView

  private val layoutManager by lazy {
    LinearLayoutManager(context)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    type = arguments.getSerializable(TYPE_ARGS) as AdType
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

    hub.connect()
  }

  private fun initView(view: View) {
    recyclerView = view.findViewById(R.id.adList)
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = adAdapter
  }

  private fun observeState() {
    disposable += state.stateChanges()
        .distinctUntilChanged()
        .doOnError { Timber.e(it) }
        .subscribe { changeState(it) }
  }

  private fun changeState(adState: AdState) {
    adAdapter.addAds(adState.ads)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposable.clear()
    hub.disconnect()
  }

  companion object {
    private const val TYPE_ARGS = "TYPE"

    fun newInstance(type: AdType): AdFragment {
      val fragment = AdFragment()

      fragment.arguments = Bundle().apply {
        putSerializable(TYPE_ARGS, type)
      }

      return fragment
    }
  }
}

enum class AdType {
  SELL, ADOPTION
}
