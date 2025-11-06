package com.example.apisenac.validation;

import com.example.apisenac.model.Lingua;

public class LinguasValidation {

    public static boolean validarLinguas(Lingua lingua){
        boolean resultado = true;
        if (lingua.getNome().isEmpty()) {
            resultado = false;
        }
        return resultado;
    }
}

