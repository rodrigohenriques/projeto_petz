package br.com.projeto.pets.data.entity

import java.math.BigInteger
import java.security.MessageDigest

data class Credential private constructor(val email: String, val password: String) {

  companion object {
    private const val ALGORITHM = "MD5"

    fun create(email: String, password: String): Credential {
      val messageDigest = MessageDigest.getInstance(ALGORITHM)
      val hash = messageDigest.digest(password.toByteArray())
      val encoded = BigInteger(1, hash).toString(16)
      return Credential(email, encoded)
    }

    private infix fun Byte.shl(shift: Int): Int = this.toInt() shl shift
  }
}
