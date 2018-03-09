package br.com.projeto.pets.infra

import br.com.projeto.pets.data.infra.UserPreference
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticatorInterceptor constructor(
    private val user: UserPreference
): Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request().newBuilder()
        .addHeader("x-access-token", user.getToken())
        .addHeader("Content-Type", "application/json")
        .build()

    return chain.proceed(request)
  }
}
