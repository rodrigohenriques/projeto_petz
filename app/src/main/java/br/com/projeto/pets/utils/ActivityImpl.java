package br.com.projeto.pets.utils;

import android.app.Activity;

/**
 * Métodos comuns para inicialização de uma Activity simples
 * Created by srolemberg on 02/02/16.
 */
public interface ActivityImpl {

    void bind();

    void listners();

    void init();

    Activity getActivity();

}
