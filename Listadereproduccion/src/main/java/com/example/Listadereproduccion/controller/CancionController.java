package com.example.Listadereproduccion.controller;

import com.example.Listadereproduccion.modelo.Cancion;
import com.example.Listadereproduccion.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cancion")
public class CancionController {
    @Autowired
    private CancionService cancionService;

    @PostMapping("/crear")
    public ResponseEntity<Cancion> crearCancion(@Validated @RequestBody Cancion cancion) {
        if (cancion.getTitulo() == null || cancion.getTitulo().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Cancion crear = cancionService.crearCancion(cancion);
        return ResponseEntity.created(
                URI.create("/canciones/" + crear.getId())
        ).body(crear);
    }

    @GetMapping
    public ResponseEntity<List<Cancion>> obtenerTodasLasCanciones() {
        return ResponseEntity.ok(cancionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> obtenerCancionPorId(@PathVariable Long id) {
        return cancionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarCancion(
            @PathVariable Long id,
            @Validated @RequestBody Cancion cancion) {
        try {
            cancionService.actualizar(id, cancion);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancion(@PathVariable Long id) {
        try {
            cancionService.eliminarCancion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}