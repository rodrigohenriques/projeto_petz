package br.com.projeto.pets.infra

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlin.properties.Delegates

open class Store<T>(initialState: T) {
  private val publisher = BehaviorSubject.create<T>()

  private var currentState by Delegates.observable(initialState) { _,_, newState ->
    newState?.let {
      publisher.onNext(it)
    }
  }

  fun update(newState: T.() -> T) {
    currentState = state().newState()
  }

  fun state() = currentState

  fun stateChanges(): Observable<T> = publisher
}
