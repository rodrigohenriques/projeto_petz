package br.com.projeto.pets.infra

import io.reactivex.Completable

interface Job<in Input> {
  fun bind(input: Input): Completable
}
