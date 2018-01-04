package br.com.projeto.pets.data.api

import br.com.projeto.pets.data.entity.Credential
import br.com.projeto.pets.data.entity.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("api/user/login")
    fun signIn(@Body credential: Credential): Single<User>
}
