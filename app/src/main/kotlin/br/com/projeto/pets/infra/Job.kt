package br.com.projeto.pets.infra

import br.com.projeto.pets.features.main.ad.AdType
import br.com.projeto.pets.features.main.ad.QueryParams
import io.reactivex.Completable

interface Job<in Input> {
    fun bind(input: Input, queryParams: QueryParams?, type: AdType): Completable
}
