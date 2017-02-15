package br.com.projeto.pets.rest.endpoint;

import br.com.projeto.pets.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserEndpoint {

    @POST("user/login")
    Call<User> login(@Body User param);

    @POST("user")
    Call<User> create(@Body User param);

}
