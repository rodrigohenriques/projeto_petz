package br.com.projeto.pets.di.module

import br.com.projeto.pets.data.api.UserApi
import br.com.projeto.pets.data.infra.UserPreference
import br.com.projeto.pets.features.ad.Ad
import br.com.projeto.pets.features.ad.AdApi
import br.com.projeto.pets.features.ad.Breed
import br.com.projeto.pets.features.ad.Category
import br.com.projeto.pets.features.ad.Photo
import br.com.projeto.pets.features.ad.Result
import br.com.projeto.pets.features.ad.User
import br.com.projeto.pets.infra.AuthenticatorInterceptor
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@Singleton
class NetworkModule {

  @Provides
  fun provideRetrofit(userPreference: UserPreference) = buildRetrofit(userPreference)
      .baseUrl("http://188.166.84.24:4600")
      .build()

  @Provides
  fun provideUserApi(retrofit: Retrofit): UserApi {
    return retrofit.create(UserApi::class.java)
  }

//  @Provides
//  fun provideAdApi(retrofit: Retrofit): AdApi {
//    return retrofit.create(AdApi::class.java)
//  }

  @Provides
  fun provideAdApi(): AdApi {
    return FakeAdApi()
  }

  private fun buildRetrofit(userPreference: UserPreference): Retrofit.Builder {
    val interceptor = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.HEADERS
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(AuthenticatorInterceptor(userPreference))
        .addInterceptor(interceptor).build()

    return Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
  }
}

class FakeAdApi: AdApi {
  override fun getAllAd(): Single<Result<Ad>> {
    val breed = Breed(10, "Pastor")

    val category = Category(1, "Category")

    val photo = Photo(10, "https://www.google.com.br/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png", "10/10")

    val photos = listOf(photo)

    val user = User("0",
        "Felipe",
        "ftgoncalves@gmail.com",
        "123",
        "Rua",
        "400",
        "Sao Paulo",
        "Sao Paulo",
        "02210-000",
        "11941445411",
        "10/01",
        "11/100",
        true)

    val ad = Ad(1,
        30,
        false,
        false,
        "São Paulo",
        "São Paulo",
        10,
        "11941447211",
        "10/10",
        true,
        breed,
        category,
        user,
        photos,
        "10")

    val ad2 = Ad(2,
        30,
        false,
        false,
        "São Paulo",
        "São Paulo",
        10,
        "11941447211",
        "10/10",
        true,
        breed,
        category,
        user,
        photos,
        "10")

    return Single.just(Result(listOf(ad, ad2)))
  }
}
