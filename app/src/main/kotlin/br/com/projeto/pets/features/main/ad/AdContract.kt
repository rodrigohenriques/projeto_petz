package br.com.projeto.pets.features.main.ad

object AdContract {
  interface View

  interface Hub {
    fun connect(queryParams: QueryParams? = QueryParams())
    fun disconnect()
  }
}
