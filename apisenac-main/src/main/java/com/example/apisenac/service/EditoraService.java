package com.example.apisenac.service;

import com.example.apisenac.exceptions.ResourceNotFoundException;
import com.example.apisenac.model.Editora;
import com.example.apisenac.repository.EditoraRepository;
import com.example.apisenac.validation.EditorasValidation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {

    private EditoraRepository editoraRepository;

    public List<Editora> listarEditoras() {
        List<Editora> editoras = this.editoraRepository.findAll();
        if (editoras.isEmpty()) {
            throw new ResourceNotFoundException("Nao ha editoras cadastradas");
        }
        return editoras;
    }

    public Editora incluirEditora(Editora editora) {
        if (EditorasValidation.validarEditoras(editora)) {
            return this.editoraRepository.save(editora);
        } else {
            throw new RuntimeException("Dados da editora invalidos");
        }
    }

    public Editora buscarEditoraPorCodigo(Long codigo) {
        String mensagem = "Editora com o codigo [" + codigo + "] nao encontrada.";
        Optional<Editora> editoras = this.editoraRepository.findById(codigo);
        if (editoras.isEmpty()) {
            throw new ResourceNotFoundException(mensagem);
        }

        Editora e = editoras.get();
        return e;
    }

    public void excluirEditora(Long codigo) {
        try {
            this.editoraRepository.deleteById(codigo);
        } catch (EmptyResultDataAccessException erdae) {
            throw new ResourceNotFoundException("Editora com o codigo [" + codigo + "nao encontrada.");
        }
    }

    public Editora atualizarEditora(Long codigo, Editora editoraAtualizada) {
        Optional<Editora> editoras = this.editoraRepository.findById(codigo);
        if (editoras.isPresent()) {
            Editora editoraAjustada = editoras.get();
            editoraAjustada.setNome(editoraAtualizada.getNome());
            return this.editoraRepository.save(editoraAjustada);
        } else {
            throw new ResourceNotFoundException("Editora com o codigo [" + codigo + "nao encontrada.");
        }
    }

}

