package br.com.projeto.pets.extension

import java.util.regex.Pattern

fun String?.isEmail(): Boolean {
  if (this == null) return false
  val emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
  return Pattern.compile(emailPattern).matcher(this).matches()
}

fun String?.isNotEmail() = isEmail().not()