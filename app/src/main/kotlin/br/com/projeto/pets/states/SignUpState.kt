package br.com.projeto.pets.states

import br.com.projeto.pets.contracts.SignUpContract

data class SignUpState(
    val data: SignUpContract.Form = SignUpContract.Form(),
    val loading: Boolean = false,
    val errors: Set<SignUpContract.Error> = emptySet()
)