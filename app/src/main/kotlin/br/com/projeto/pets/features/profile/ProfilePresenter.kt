package br.com.projeto.pets.features.profile

import br.com.projeto.pets.R
import br.com.projeto.pets.data.entity.User
import br.com.projeto.pets.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfilePresenter @Inject constructor(val view: ProfileContract.View,
                                           private val userRepository: UserRepository) :
        ProfileContract.Presenter {

    override fun onCreate() {
        fetchData()
    }

    override fun onDestroy() {}

    override fun fetchData() {
        userRepository.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading() }
                .subscribe({ view.userData(it) }, { view.error(R.string.user_error) })
    }

    override fun updateUser(phone: String, city: String, state: String) {
        userRepository.updateUser(phone, city, state)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading() }
                .subscribe({ view.userUpdated() }, { view.error(R.string.user_update_error) })
    }

}