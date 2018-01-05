package br.com.projeto.pets

import br.com.projeto.pets.contracts.SignUpContract
import br.com.projeto.pets.states.SignUpState

class SignUpStateManager : StateManager<SignUpState>(SignUpState()) {
  fun updateData(form: SignUpContract.Form) {
    val errors = form.validate()
    val newState = state().copy(data = form, errors = errors)
    update(newState)
  }

  fun setLoading(isLoading: Boolean) {
    val newState = state().copy(loading = isLoading)
    update(newState)
  }
}
