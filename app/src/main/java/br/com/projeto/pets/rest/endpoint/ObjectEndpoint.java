package br.com.projeto.pets.rest.endpoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ObjectEndpoint {

    @GET("object/")
    Call<List<Object>> listObject(@Query("param") String param);

}
