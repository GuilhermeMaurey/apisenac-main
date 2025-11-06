package com.example.apisenac.service;

import com.example.apisenac.exceptions.ResourceNotFoundException;
import com.example.apisenac.model.Lingua;
import com.example.apisenac.repository.LinguaRepository;
import com.example.apisenac.validation.LinguasValidation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinguaService {

    private LinguaRepository linguaRepository;

    public List<Lingua> listarLinguas() {
        List<Lingua> linguas = this.linguaRepository.findAll();
        if (linguas.isEmpty()) {
            throw new ResourceNotFoundException("Nao ha linguas cadastradas");
        }
        return linguas;
    }

    public Lingua incluirLingua(Lingua lingua) {
        if (LinguasValidation.validarLinguas(lingua)) {
            return this.linguaRepository.save(lingua);
        } else {
            throw new RuntimeException("Dados da lingua invalidos");
        }
    }

    public Lingua buscarLinguaPorCodigo(Integer codigo) {
        String mensagem = "Lingua com o codigo [" + codigo + "] nao encontrada.";
        Optional<Lingua> linguas = this.linguaRepository.findById(codigo);
        if (linguas.isEmpty()) {
            throw new ResourceNotFoundException(mensagem);
        }

        Lingua l = linguas.get();
        return l;
    }

    public void excluirLingua(Integer codigo) {
        try {
            this.linguaRepository.deleteById(codigo);
        } catch (EmptyResultDataAccessException erdae) {
            throw new ResourceNotFoundException("Lingua com o codigo [" + codigo + "nao encontrada.");
        }
    }

    public Lingua atualizarLingua(Integer codigo, Lingua linguaAtualizada) {
        Optional<Lingua> linguas = this.linguaRepository.findById(codigo);
        if (linguas.isPresent()) {
            Lingua linguaAjustada = linguas.get();
            linguaAjustada.setNome(linguaAtualizada.getNome());
            return this.linguaRepository.save(linguaAjustada);
        } else {
            throw new ResourceNotFoundException("Lingua com o codigo [" + codigo + "nao encontrada.");
        }
    }

}

