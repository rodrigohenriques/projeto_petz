package br.com.projeto.pets.utils;

import io.realm.RealmObject;

/**
 * Métodos comuns para inicialização de uma Activity simples
 * Created by srolemberg on 02/02/16.
 */
public interface ParseToObject<T, U extends RealmObject> {
    T fromRealm(U realmObject);
}
