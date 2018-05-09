package br.com.projeto.pets.di.module

import android.content.Context
import br.com.projeto.pets.BuildConfig
import br.com.projeto.pets.data.infra.UserPreference
import br.com.projeto.pets.infra.AuthenticatorInterceptor
import br.com.projeto.pets.infra.UnauthenticatedInterceptor
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@Singleton
class NetworkModule {

  @Provides
  @Named(GENERAL)
  fun provideRetrofit(userPreference: UserPreference) = buildRetrofit(userPreference)
      .baseUrl(BuildConfig.HOST_URL)
      .build()

  @Provides
  @Named(LOGIN)
  fun provideRetrofitForLogin(userPreference: UserPreference, context: Context): Retrofit {
    val unauthenticatedInterceptor = UnauthenticatedInterceptor(context, userPreference)

    return buildRetrofit(userPreference, unauthenticatedInterceptor)
        .baseUrl(BuildConfig.HOST_URL)
        .build()
  }

  private fun buildRetrofit(userPreference: UserPreference, interceptor: Interceptor? = null): Retrofit.Builder {
    fun getOkHttpClient(): OkHttpClient  {
      return OkHttpClient.Builder().apply {
        addInterceptor(AuthenticatorInterceptor(userPreference))

        addInterceptor(HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.BODY
        })

        interceptor?.let {
          this.addInterceptor(it)
        }
      }.build()
    }

    return Retrofit.Builder()
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
  }

  companion object {
    const val GENERAL = "GENERAL"
    const val LOGIN = "LOGIN"
  }
}
