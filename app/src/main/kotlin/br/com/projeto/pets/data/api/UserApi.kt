package br.com.projeto.pets.data.api

import br.com.projeto.pets.data.entities.CreateUser
import br.com.projeto.pets.data.entities.User
import io.reactivex.Single
import retrofit2.http.POST

interface UserApi {
  @POST("api/user")
  fun createUser(createUser: CreateUser): Single<User>
}
