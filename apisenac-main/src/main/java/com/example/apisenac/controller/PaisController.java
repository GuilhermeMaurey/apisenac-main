package com.example.apisenac.controller;

import com.example.apisenac.exceptions.ResourceNotFoundException;
import com.example.apisenac.model.Pais;
import com.example.apisenac.service.PaisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisController {

    private PaisService paisService;

    @GetMapping("/paises")
    public ResponseEntity<?> listarPaises() {
        try {
            return ResponseEntity.ok(this.paisService.listarPaises());
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    @PostMapping("/pais")
    public ResponseEntity<?> incluirPais(@RequestBody Pais pais) {
        try {
            Pais paisNovo = this.paisService.incluirPais(pais);
            return ResponseEntity.status(HttpStatus.OK).body(paisNovo);
        } catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
        }
    }

    @PutMapping("/pais/{codigo}")
    public ResponseEntity<?> atualizarPais(@PathVariable Integer codigo, @RequestBody Pais pais) {
        try {
            Pais paisAtualizado = this.paisService.atualizarPais(codigo, pais);
            return ResponseEntity.status(HttpStatus.OK).body(paisAtualizado);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    @DeleteMapping("/paises/{codigo}")
    public ResponseEntity<?> excluirPais(@PathVariable Integer codigo) {
        try {
            this.paisService.excluirPais(codigo);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }

    @GetMapping("/paises/{codigo}")
    public ResponseEntity<?> buscarPaisPorCodigo(@PathVariable Integer codigo) {
        try {
            Pais pais = this.paisService.buscarPaisPorCodigo(codigo);
            return ResponseEntity.ok(pais);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }
}


