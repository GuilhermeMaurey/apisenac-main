package com.example.apisenac.service;

import com.example.apisenac.exceptions.ResourceNotFoundException;
import com.example.apisenac.model.Pais;
import com.example.apisenac.repository.PaisRepository;
import com.example.apisenac.validation.PaisesValidation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {

    private PaisRepository paisRepository;

    public List<Pais> listarPaises() {
        List<Pais> pais = this.paisRepository.findAll();
        if (pais.isEmpty()) {
            throw new ResourceNotFoundException("Nao ha pais cadastrados.");
        }
        return pais;
    }

    public Pais incluirPais(Pais pais) {
        if (PaisesValidation.validarPaises(pais)) {
            return this.paisRepository.save(pais);
        } else {
            throw new RuntimeException("Dados do Pais invalidos");
        }
    }

    public Pais buscarPaisPorCodigo(Integer codigo) {
        String mensagem = "Pais com o codigo [" + codigo + "] nao encontrado.";
        Optional<Pais> paises = this.paisRepository.findById(codigo);
        if (paises.isEmpty()) {
            throw new ResourceNotFoundException(mensagem);
        }

        Pais p = paises.get();
        return p;
    }

    public void excluirPais(Integer codigo) {
        try {
            this.paisRepository.deleteById(codigo);
        } catch (EmptyResultDataAccessException erdae) {
            throw new ResourceNotFoundException("Pais com o codigo [" + codigo + "nao encontrado.");
        }
    }

    public Pais atualizarPais(Integer codigo, Pais paisAtualizado) {
        Optional<Pais> paises = this.paisRepository.findById(codigo);
        if (paises.isPresent()) {
            Pais paisAjustado = paises.get();
            paisAjustado.setNome(paisAtualizado.getNome());
            return this.paisRepository.save(paisAjustado);
        } else {
            throw new ResourceNotFoundException("Pais com o codigo [" + codigo + "nao encontrado.");
        }
    }

}

