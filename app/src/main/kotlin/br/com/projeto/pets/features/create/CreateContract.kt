package br.com.projeto.pets.features.create

import br.com.projeto.pets.data.entity.Breed
import com.yanzhenjie.album.AlbumFile


interface CreateContract {
    interface View {
        fun getAlbumImages(): List<AlbumFile>
        fun getTypeAd(): Int
        fun isHatch(): Boolean
        fun isPedigree(): Boolean
        fun isCastration(): Boolean
        fun getAge(): Int
        fun getCity(): String
        fun getState(): String
        fun getPrice(): String
        fun getPhone(): String
        fun success()
        fun error()
        fun getBreedId(): Int
        fun isVaccinated(): Boolean
    }

    interface Presenter {
        val breedList: List<Breed>
        fun sendRequest()
    }
}