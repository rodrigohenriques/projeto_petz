package br.com.projeto.pets.di.module

import br.com.projeto.pets.data.api.UserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class UserModule {
  @Provides
  fun provideUserApi(@Named(NetworkModule.GENERAL) retrofit: Retrofit): UserApi {
    return retrofit.create(UserApi::class.java)
  }
}
