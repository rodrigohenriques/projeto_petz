package br.com.projeto.pets.features.profile

import br.com.projeto.pets.data.entity.User
import br.com.projeto.pets.presenter.PresenterActivity

interface ProfileContract {
    interface View {
        fun userData(user: User)
        fun error(message: Int)
        fun showLoading()
        fun userUpdated()
    }

    interface Presenter : PresenterActivity {
        fun fetchData()
        fun updateUser(phone: String, city: String, state: String)
    }
}