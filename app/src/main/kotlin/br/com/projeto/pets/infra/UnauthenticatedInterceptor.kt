package br.com.projeto.pets.infra

import android.content.Context
import android.content.Intent
import br.com.projeto.pets.data.infra.UserPreference
import br.com.projeto.pets.ui.activity.LoginActivity
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection



class UnauthenticatedInterceptor constructor(
    private val context: Context,
    private val userPreference: UserPreference
) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val response = chain.proceed(chain.request())

    if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
      userPreference.clear()

      val intent = Intent(context, LoginActivity::class.java)
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

      context.startActivity(intent)
    }

    return response
  }
}
