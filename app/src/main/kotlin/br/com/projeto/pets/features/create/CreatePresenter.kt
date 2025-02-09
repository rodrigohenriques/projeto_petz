package br.com.projeto.pets.features.create

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import br.com.projeto.pets.data.api.CreateApi
import br.com.projeto.pets.data.entity.AdCreateModel
import br.com.projeto.pets.data.infra.UserPreference
import br.com.projeto.pets.data.entity.AgeClassification
import br.com.projeto.pets.data.entity.Breed
import br.com.projeto.pets.data.entity.Photo
import br.com.projeto.pets.util.Base64Convertor.encodeToBase64
import io.paperdb.Paper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreatePresenter @Inject constructor(private val view: CreateContract.View,
                                          private val createApi: CreateApi,
                                          private val userPreference: UserPreference) :
        CreateContract.Presenter {

    override val breedList: List<Breed> = Paper.book().read("breed")

    override fun sendRequest() {
        view.showLoading()

        createApi.createAd(createData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.success() }, { view.error() })
    }

    private fun createData(): AdCreateModel {
        val photos = view
                .getAlbumImages()
                .map { Photo(null, encodeToBase64(BitmapFactory.decodeFile(it.path),
                        Bitmap.CompressFormat.PNG, 100), null) }

        return AdCreateModel(
                view.getAge(),
                AgeClassification.classificationFromAge(view.getAge()),
                view.isHatch(),
                view.isVaccinated(),
                view.getState(),
                view.getCity(),
                view.getPrice(),
                view.getPhone(),
                true,
                view.getBreedId(),
                view.getCategoryId(),
                userPreference.getUserId(),
                view.getDescription(),
                photos)
    }
}