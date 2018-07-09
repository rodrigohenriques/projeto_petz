package br.com.projeto.pets.features.init.signup

import br.com.projeto.pets.features.init.signup.SignUpContract

data class SignUpState(
        val data: SignUpContract.Data = SignUpContract.Data(),
        val loading: Boolean = false,
        val errors: Set<SignUpContract.Error> = emptySet()
) {
  fun hasNoErrors() = errors.isEmpty()
}