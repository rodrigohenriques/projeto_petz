package br.com.projeto.pets.features.ad

object AdContract {
  interface View

  interface Hub {
    fun connect(breedId: Int? = null,
                ageClassificationId: Int? = null)

    fun disconnect()
  }
}
