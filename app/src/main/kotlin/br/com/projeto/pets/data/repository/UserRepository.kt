package br.com.projeto.pets.data.repository

import br.com.projeto.pets.data.api.UserApi
import br.com.projeto.pets.data.entity.Credential
import br.com.projeto.pets.data.entity.NewUser
import br.com.projeto.pets.data.entity.User
import br.com.projeto.pets.data.infra.UserPreference
import io.paperdb.Paper
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val userApi: UserApi,
        private val userPreference: UserPreference
) {
    fun signIn(credential: Credential): Completable {
        return userApi.signIn(credential)
                .flatMap {
                    userPreference.saveUserId(it.id)
                    it.session?.token?.let { it1 -> userPreference.saveToken(it1) }
                    it.name?.let { it1 -> userPreference.saveName(it1) }
                    it.email?.let { it1 -> userPreference.saveEmail(it1) }
                    userApi.getBreedList()
                }
                .doOnSuccess {
                    Paper.book().destroy()
                    Paper.book().write("breed", it)
                }
                .doOnError {
                    userPreference.clear()
                    Timber.e(it)
                }
                .toCompletable()
    }

    fun createUser(newUser: NewUser): Completable {
        return userApi.createUser(newUser)
                .map { Credential(newUser.email, newUser.password) }
                .flatMapCompletable { signIn(it) }
                .doOnError { Timber.e(it) }
    }

    fun updateUser(phone: String, city: String, state: String) : Completable {
        val user = User(id = userPreference.getUserId(),
                email = userPreference.getEmail(),
                name = userPreference.getName(),
                phone = phone, city = city, state = state)
        return Completable.fromSingle(userApi.updateUser(userPreference.getUserId(), user))
    }

    fun getUser() : Single<User> {
        return userApi.getUser(userPreference.getEmail())
    }
}
