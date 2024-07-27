package com.example.Listadereproduccion.service;

import com.example.Listadereproduccion.dao.CancionesDao;
import com.example.Listadereproduccion.modelo.Cancion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {
    @Autowired
    private CancionesDao cancionDao;

    public Cancion crearCancion(Cancion cancion) {
        return cancionDao.save(cancion);
    }

    public List<Cancion> listar() {
        return cancionDao.findAll();
    }

    public Optional<Cancion> obtenerPorId(Long id) {
        return cancionDao.findById(id);
    }

    public void actualizar(Long id, Cancion cancion) {
        if (cancionDao.existsById(id)) {
            Cancion existingCancion = cancionDao.findById(id).orElseThrow();
            existingCancion.setTitulo(cancion.getTitulo());
            existingCancion.setArtista(cancion.getArtista());
            existingCancion.setAlbum(cancion.getAlbum());
            cancionDao.save(existingCancion);
        } else {
            throw new RuntimeException("No existe la cancion");
        }
    }

    public void eliminarCancion(Long id) {
        if (cancionDao.existsById(id)) {
            cancionDao.deleteById(id);
        } else {
            throw new RuntimeException("No existe la cancion");
        }
    }
}
