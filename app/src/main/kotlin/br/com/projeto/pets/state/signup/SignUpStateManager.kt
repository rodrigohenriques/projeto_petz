package br.com.projeto.pets.state.signup

import br.com.projeto.pets.contract.SignUpContract
import br.com.projeto.pets.state.StateManager
import javax.inject.Inject

class SignUpStateManager @Inject constructor() : StateManager<SignUpState>(SignUpState()) {
  fun updateData(data: SignUpContract.Data) {
    val errors = data.validate()
    val newState = state().copy(data = data, errors = errors)
    update(newState)
  }

  fun setLoading(isLoading: Boolean) {
    val newState = state().copy(loading = isLoading)
    update(newState)
  }

  fun setError(exception: Throwable) {
    val error = SignUpContract.Error.evaluate(exception)
    setError(error)
  }

  fun setError(error: SignUpContract.Error) {
    val errors = setOf(error)
    val newState = state().copy(errors = errors)
    update(newState)
  }
}
