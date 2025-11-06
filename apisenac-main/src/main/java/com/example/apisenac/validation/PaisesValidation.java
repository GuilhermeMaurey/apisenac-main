package com.example.apisenac.validation;

import com.example.apisenac.model.Pais;

public class PaisesValidation {

    public static boolean validarPaises(Pais pais){
        boolean resultado = true;
        if (pais.getNome().isEmpty()) {
            resultado = false;
        }
        return resultado;
    }
}
