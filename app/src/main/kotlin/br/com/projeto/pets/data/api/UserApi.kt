package br.com.projeto.pets.data.api

import br.com.projeto.pets.data.entity.Credential
import br.com.projeto.pets.data.entity.NewUser
import br.com.projeto.pets.data.entity.User
import br.com.projeto.pets.data.entity.Breed
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
  @POST("api/user/login")
  fun signIn(@Body credential: Credential): Single<User>

  @POST("api/user")
  fun createUser(@Body newUser: NewUser): Single<User>

  @GET("api/breed")
  fun getBreedList(): Single<List<Breed>>
}
