package br.com.projeto.pets.infra

import br.com.projeto.pets.features.ad.QueryParams
import io.reactivex.Completable

interface Job<in Input> {
    fun bind(input: Input, queryParams: QueryParams?): Completable
}
