package br.com.projeto.pets.extension

import org.json.JSONObject
import retrofit2.HttpException

fun HttpException.errorMessage(): String? {
  return try {
    val errorBodyJson = this.response().errorBody()?.string()
    JSONObject(errorBodyJson).optString("message")
  } catch (e: Throwable) {
    null
  }
}
