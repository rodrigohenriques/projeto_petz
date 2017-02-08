package br.com.projeto.pets.utils;

import io.realm.RealmObject;

/**
 * Métodos comuns para inicialização de uma Activity simples
 * Created by srolemberg on 02/02/16.
 */
public interface ParseToRealm<T extends RealmObject, U> {
    T fromObject(U model);
}
