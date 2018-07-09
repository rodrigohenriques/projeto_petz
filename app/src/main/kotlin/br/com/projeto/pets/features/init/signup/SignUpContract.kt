package br.com.projeto.pets.features.init.signup

import android.content.Context
import br.com.projeto.pets.R
import br.com.projeto.pets.features.init.signup.SignUpContract.Error.EMAIL_CONFIRMATION_MISMATCH
import br.com.projeto.pets.features.init.signup.SignUpContract.Error.EMPTY_EMAIL
import br.com.projeto.pets.features.init.signup.SignUpContract.Error.EMPTY_EMAIL_CONFIRMATION
import br.com.projeto.pets.features.init.signup.SignUpContract.Error.EMPTY_NAME
import br.com.projeto.pets.features.init.signup.SignUpContract.Error.EMPTY_PASSWORD
import br.com.projeto.pets.features.init.signup.SignUpContract.Error.INVALID_EMAIL
import br.com.projeto.pets.features.init.signup.SignUpContract.Error.INVALID_EMAIL_CONFIRMATION
import br.com.projeto.pets.extension.errorMessage
import br.com.projeto.pets.extension.isNotEmail
import br.com.projeto.pets.presenter.PresenterActivity
import io.reactivex.Observable
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object SignUpContract {
  interface View {
    fun createAccountClicks(): Observable<Data>
    fun startSession()
  }

  interface Presenter : PresenterActivity

  data class Data(
      val name: String? = null,
      val password: String? = null,
      val email: String? = null,
      val emailConfirmation: String? = null
  ) {

    fun validate(): Set<Error> {
      val errors = mutableSetOf<Error>()

      if (name.isNullOrEmpty()) {
        errors.add(EMPTY_NAME)
      }

      if (password.isNullOrEmpty()) {
        errors.add(EMPTY_PASSWORD)
      }

      when {
        email.isNullOrEmpty() -> errors.add(EMPTY_EMAIL)
        email.isNotEmail() -> errors.add(INVALID_EMAIL)
      }

      when {
        emailConfirmation.isNullOrEmpty() -> errors.add(EMPTY_EMAIL_CONFIRMATION)
        emailConfirmation.isNotEmail() -> errors.add(INVALID_EMAIL_CONFIRMATION)
      }

      email?.let { email ->
        emailConfirmation?.let { confirmation ->
          if (email != confirmation) {
            errors.add(EMAIL_CONFIRMATION_MISMATCH)
          }
        }
      }

      return errors
    }
  }

  enum class Error(
      private val messageResId: Int = R.string.error_unknown_failure,
      private var messageRaw: String? = null
  ) {
    EMPTY_NAME(R.string.error_required_field),
    EMPTY_PASSWORD(R.string.error_required_field),
    EMPTY_EMAIL(R.string.error_required_field),
    EMPTY_EMAIL_CONFIRMATION(R.string.error_required_field),
    INVALID_PASSWORD(R.string.error_invalid_password),
    EMAIL_CONFIRMATION_MISMATCH(R.string.error_email_confirmation_mismatch),
    INVALID_EMAIL(R.string.error_invalid_email),
    INVALID_EMAIL_CONFIRMATION(R.string.error_invalid_email),
    HTTP_EXCEPTION(R.string.error_http_connection_failed),
    NO_CONNECTION(R.string.error_no_connection),
    UNKNOWN_FAILURE;

    fun getMessage(context: Context): String {
      return messageRaw ?: context.getString(messageResId)
    }

    companion object {
      fun evaluate(throwable: Throwable): Error {
        return when (throwable) {
          is HttpException -> {
            HTTP_EXCEPTION.apply {
              messageRaw = throwable.errorMessage()
            }
          }
          is SocketTimeoutException, is SocketException, is UnknownHostException -> NO_CONNECTION
          else -> UNKNOWN_FAILURE
        }
      }
    }
  }
}
