package br.com.projeto.pets.data.api

import br.com.projeto.pets.data.entity.Credential
import br.com.projeto.pets.data.entity.NewUser
import br.com.projeto.pets.data.entity.User
import br.com.projeto.pets.data.entity.Breed
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface UserApi {
  @POST("api/user/login")
  fun signIn(@Body credential: Credential): Single<User>

  @POST("api/user")
  fun createUser(@Body newUser: NewUser): Single<User>

  @GET("api/breed")
  fun getBreedList(): Single<List<Breed>>

  @GET("api/user/{email}")
  fun getUser(@Path("email") email : String): Single<User>

  @PUT("api/user/{id}")
  fun updateUser(@Path("id") id: Int, @Body user: User) : Single<ResponseBody>
}
