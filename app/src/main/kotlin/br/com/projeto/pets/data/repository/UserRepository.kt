package br.com.projeto.pets.data.repository

import br.com.projeto.pets.data.api.UserApi
import br.com.projeto.pets.data.entity.Credential
import br.com.projeto.pets.data.entity.NewUser
import br.com.projeto.pets.data.infra.UserPreference
import br.com.projeto.pets.features.ad.Breed
import io.paperdb.Paper
import io.reactivex.Completable
import timber.log.Timber
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val userApi: UserApi,
        private val userPreference: UserPreference
) {
    fun signIn(credential: Credential): Completable {
        return userApi.signIn(credential)
                .flatMap {
                    userPreference.saveToken(it.session.token)
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
}
