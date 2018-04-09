package br.com.projeto.pets.infra

import io.reactivex.Completable

interface Job<in Input> {
    fun bind(input: Input, breedId: String? = null,
             ageClassificationId: String? = null): Completable
}
