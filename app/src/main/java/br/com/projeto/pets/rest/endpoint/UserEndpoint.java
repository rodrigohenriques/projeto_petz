package br.com.projeto.pets.rest.endpoint;

import br.com.projeto.pets.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserEndpoint {

    @POST("user/login")
    Call<User> login(@Body User param);

    @POST("user")
    Call<User> create(@Body User param);

    @GET("user/{email}")
    Call<User> get(@Path("email") String email);

    @PUT("user/{id}")
    Call<Void> update(@Path("id") String id, @Body User param);

}
