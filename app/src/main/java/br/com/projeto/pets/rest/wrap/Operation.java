package br.com.projeto.pets.rest.wrap;


import br.com.projeto.pets.helper.RetrofitHelper;
import br.com.projeto.pets.utils.Util;

public class Operation<T> {

    private T service;

    private Operation() {
    }

    public Operation(Class<T> clazz) {
        service = RetrofitHelper.getInstance("").create(clazz);
    }

    public T create() {
        return service;
    }
}