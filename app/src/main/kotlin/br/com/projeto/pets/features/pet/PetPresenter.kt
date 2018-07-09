package br.com.projeto.pets.features.pet

import br.com.projeto.pets.data.api.PetApi
import br.com.projeto.pets.data.entity.Ad
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PetPresenter @Inject constructor(
        private val view: PetContract.View,
        private val petApi: PetApi
) : PetContract.Presenter {
    override fun onStart(petId: Int) {
        petApi
                .getPedById(petId.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::success, this::error)
    }

    private fun success(ad: Ad) {
        view.success(ad)
    }

    private fun error(throwable: Throwable) {
        Timber.e(throwable)
        view.error(throwable)
    }

}