package br.com.projeto.pets.data.entity

import java.security.MessageDigest

data class Credential private constructor(val email: String, val password: String) {

  companion object {
    private const val ALGORITHM = "MD5"

    fun create(email: String, password: String): Credential {
      val messageDigest = MessageDigest.getInstance(ALGORITHM)
      val cryptedPassword = messageDigest.digest(password.toByteArray()).toString()

      return Credential(email, cryptedPassword)
    }
  }
}
