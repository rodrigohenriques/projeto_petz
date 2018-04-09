package br.com.projeto.pets.features.filter

import br.com.projeto.pets.data.repository.UserRepository
import br.com.projeto.pets.features.ad.AdType
import br.com.projeto.pets.features.pet.FilterContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilterPresenter @Inject constructor(
        private val userRepository: UserRepository
) : FilterContract.Presenter {
    private var adType: String = "NONE"
    private var breedId: Int = 0

    override fun getType(): String = adType


    override fun getBreedId(): String = breedId.toString()


    override fun setType(adType: AdType?) {
        this.adType = adType.toString()
    }

    override fun setBreedId(breedId: Int) {
        this.breedId = breedId
    }


    override fun onDestroy() {
    }

    override fun onCreate() {
    }


}