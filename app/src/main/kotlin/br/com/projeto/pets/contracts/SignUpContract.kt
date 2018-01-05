package br.com.projeto.pets.contracts

import br.com.projeto.pets.PresenterActivity
import br.com.projeto.pets.extension.isNotEmail
import io.reactivex.Observable

object SignUpContract {
  interface View {
    fun createAccountClicks(): Observable<Form>
  }

  interface Presenter : PresenterActivity

  data class Form(
      val username: String? = null,
      val password: String? = null,
      val email: String? = null,
      val emailConfirmation: String? = null
  ) {

    fun validate(): Set<Error> {
      val errors = mutableSetOf<Error>()

      if (username.isNullOrEmpty()) {
        errors.add(Error.EMPTY_USERNAME)
      }

      if (password.isNullOrEmpty()) {
        errors.add(Error.EMPTY_PASSWORD)
      }

      when {
        email.isNullOrEmpty() -> errors.add(Error.EMPTY_EMAIL)
        email.isNotEmail() -> errors.add(Error.INVALID_EMAIL)
      }

      when {
        emailConfirmation.isNullOrEmpty() -> errors.add(Error.EMPTY_EMAIL_CONFIRMATION)
        emailConfirmation.isNotEmail() -> errors.add(Error.INVALID_EMAIL_CONFIRMATION)
      }

      email?.let { email ->
        emailConfirmation?.let { confirmation ->
          if (email != confirmation) {
            errors.add(Error.EMAIL_CONFIRMATION_MISMATCH)
          }
        }
      }

      return errors
    }
  }

  enum class Error {
    EMPTY_USERNAME, EMPTY_PASSWORD,
    EMPTY_EMAIL, EMPTY_EMAIL_CONFIRMATION,
    INVALID_PASSWORD, INVALID_USERNAME,
    EMAIL_CONFIRMATION_MISMATCH,
    INVALID_EMAIL,
    INVALID_EMAIL_CONFIRMATION
  }
}
