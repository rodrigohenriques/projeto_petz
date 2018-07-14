package br.com.projeto.pets.features.main.ad

object AdContract {
  interface View

  interface Hub {
    fun connect(queryParams: QueryParams? = QueryParams(), type: AdType)
    fun disconnect()
  }
}
