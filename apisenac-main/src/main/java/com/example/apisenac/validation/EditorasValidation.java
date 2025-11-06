package com.example.apisenac.validation;

import com.example.apisenac.model.Editora;

public class EditorasValidation {

    public static boolean validarEditoras(Editora editora) {
        boolean resultado = true;
        if (editora.getNome().isEmpty()) {
            resultado = false;
        }
        return resultado;
    }
}