package br.com.projeto.pets.features.create

import br.com.projeto.pets.data.entity.Breed
import com.yanzhenjie.album.AlbumFile


interface CreateContract {
    interface View {
        fun getAlbumImages(): List<AlbumFile>
        fun isHatch(): Boolean
        fun getAge(): Int
        fun getCity(): String
        fun getState(): String
        fun getPrice(): Float
        fun getPhone(): String
        fun success()
        fun error()
        fun getBreedId(): Int
        fun isVaccinated(): Boolean
        fun getCategoryId() : Int
        fun showLoading()
    }

    interface Presenter {
        val breedList: List<Breed>
        fun sendRequest()
    }
}