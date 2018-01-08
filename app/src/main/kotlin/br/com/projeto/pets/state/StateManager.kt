package br.com.projeto.pets.state

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlin.properties.Delegates


open class StateManager<T>(initialState: T) {
  private val publisher = BehaviorSubject.create<T>()

  private var currentState by Delegates.observable(initialState) { _, _, newState ->
   newState?.let { publisher.onNext(it) }
  }

  fun update(state: T) {
    currentState = state
  }

  fun state() = currentState

  fun stateChanges(): Observable<T> = publisher
}