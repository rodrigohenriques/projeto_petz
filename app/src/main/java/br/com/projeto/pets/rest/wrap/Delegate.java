package br.com.projeto.pets.rest.wrap;

public interface Delegate<T> {

    void onSuccess(T object);

    void onError(String message);

}