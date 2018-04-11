package br.com.projeto.pets.infra

import io.reactivex.Completable

interface Job<in Input> {
    fun bind(input: Input, breedId: Int? = null,
             ageClassificationId: Int? = null): Completable
}
