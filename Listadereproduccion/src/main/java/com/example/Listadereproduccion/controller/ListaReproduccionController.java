package com.example.Listadereproduccion.controller;

import com.example.Listadereproduccion.modelo.ListaReproduccion;
import com.example.Listadereproduccion.service.ListaReproduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lista")
public class ListaReproduccionController {
    @Autowired
    private ListaReproduccionService listaService;

    @PostMapping("/crear")
    public ResponseEntity<ListaReproduccion> crearPlaylist(@Validated @RequestBody ListaReproduccion lista) {
        if (lista.getNombre() == null || lista.getNombre().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ListaReproduccion crear = listaService.crear(lista);
        return ResponseEntity.created(
                URI.create("/lista/" + crear.getNombre())
        ).body(crear);
    }

    @GetMapping
    public ResponseEntity<List<ListaReproduccion>> obtenerTodasLasPlaylists() {
        return ResponseEntity.ok(listaService.obtenerPistas());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<ListaReproduccion> obtenerPlaylistPorNombre(@PathVariable String nombre) {
        ListaReproduccion playlist = listaService.obtenerNombre(nombre);
        if (playlist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(playlist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarPlaylist(
            @PathVariable Long id,
            @RequestBody ListaReproduccion playlist) {
        if (playlist.getNombre() != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        try {
            listaService.actualizar(id, playlist);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            listaService.eliminarPlaylist(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
