package br.com.projeto.pets.data.repository

import br.com.projeto.pets.data.api.UserApi
import br.com.projeto.pets.data.entity.Credential
import br.com.projeto.pets.data.infra.UserPreference
import io.reactivex.Completable
import timber.log.Timber
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val userApi: UserApi,
        private val userPreference: UserPreference
) {
    fun signIn(credential: Credential): Completable {
        return userApi.signIn(credential)
                .doOnSuccess { userPreference.saveToken(it.session.token) }
                .doOnError { Timber.e(it) }
                .toCompletable()
    }
}
