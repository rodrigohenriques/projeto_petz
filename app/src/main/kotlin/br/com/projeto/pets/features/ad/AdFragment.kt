package br.com.projeto.pets.features.ad

import android.os.Bundle
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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    type = arguments.getSerializable(TYPE_ARGS) as AdType
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.fragment_ad, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    observeState()

    hub.connect()
  }

  private fun observeState() {
    disposable += state.stateChanges()
        .subscribe { changeState(it) }
  }

  private fun changeState(adState: AdState) {
    Timber.d(adState.toString())
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
