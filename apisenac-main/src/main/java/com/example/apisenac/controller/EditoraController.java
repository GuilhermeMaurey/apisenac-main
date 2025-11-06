package com.example.apisenac.controller;

import com.example.apisenac.exceptions.ResourceNotFoundException;
import com.example.apisenac.model.Editora;
import com.example.apisenac.service.EditoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EditoraController {

    private EditoraService editoraService;

    @GetMapping("/editoras")
    public ResponseEntity<?> listarEditoras() {
        try {
            return ResponseEntity.ok(this.editoraService.listarEditoras());
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> incluirEditora(@RequestBody Editora editora) {
        try {
            Editora editoraNova = this.editoraService.incluirEditora(editora);
            return ResponseEntity.status(HttpStatus.OK).body(editoraNova);
        } catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
        }
    }

    @PutMapping("/editoras/{codigo}")
    public ResponseEntity<?> atualizarEditora(@PathVariable Long codigo, @RequestBody Editora editora) {
        try {
            Editora editoraAtualizada = this.editoraService.atualizarEditora(codigo, editora);
            return ResponseEntity.status(HttpStatus.OK).body(editoraAtualizada);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    @DeleteMapping("/editoras/{codigo}")
    public ResponseEntity<?> excluirEditora(@PathVariable Long codigo) {
        try {
            this.editoraService.excluirEditora(codigo);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }

    @GetMapping("/editoras/{codigo}")
    public ResponseEntity<?> buscarEditoraPorCodigo(@PathVariable Long codigo) {
        try {
            Editora editora = this.editoraService.buscarEditoraPorCodigo(codigo);
            return ResponseEntity.ok(editora);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }
}

