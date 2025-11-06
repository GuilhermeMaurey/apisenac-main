package com.example.apisenac.controller;

import com.example.apisenac.exceptions.ResourceNotFoundException;
import com.example.apisenac.model.Lingua;
import com.example.apisenac.service.LinguaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/linguas")
public class LinguaController {

    private LinguaService linguaService;

    @GetMapping("/linguas")
    public ResponseEntity<?> listaLinguas() {
        try {
            return ResponseEntity.ok(this.linguaService.listarLinguas());
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    @PostMapping("/lingua")
    public ResponseEntity<?> incluirLingua(@RequestBody Lingua lingua) {
        try {
            Lingua linguaNova = this.linguaService.incluirLingua(lingua);
            return ResponseEntity.status(HttpStatus.OK).body(linguaNova);
        } catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
        }
    }

    @PutMapping("/lingua/{codigo}")
    public ResponseEntity<?> atualizarLingua(@PathVariable Integer codigo, @RequestBody Lingua lingua) {
        try {
            Lingua linguaAtualizada = this.linguaService.atualizarLingua(codigo, lingua);
            return ResponseEntity.status(HttpStatus.OK).body(linguaAtualizada);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    @DeleteMapping("/linguas/{codigo}")
    public ResponseEntity<?> excluirLingua(@PathVariable Integer codigo) {
        try {
            this.linguaService.excluirLingua(codigo);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }

    @GetMapping("/linguas/{codigo}")
    public ResponseEntity<?> buscarLinguaPorCodigo(@PathVariable Integer codigo) {
        try {
            Lingua lingua = this.linguaService.buscarLinguaPorCodigo(codigo);
            return ResponseEntity.ok(lingua);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }
}
