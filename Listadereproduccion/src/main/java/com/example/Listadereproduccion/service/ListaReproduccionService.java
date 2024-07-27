package com.example.Listadereproduccion.service;

import com.example.Listadereproduccion.dao.ListaReproduccionDao;
import com.example.Listadereproduccion.modelo.ListaReproduccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaReproduccionService {
    @Autowired
    private ListaReproduccionDao listaDao;

    public ListaReproduccion crear(ListaReproduccion lista) {
        return listaDao.save(lista);
    }

    public List<ListaReproduccion> obtenerPistas() {
        return listaDao.findAll();
    }

    public ListaReproduccion obtenerNombre(String nombre) {
        return listaDao.findByNombre(nombre);
    }

    public void actualizar(Long id, ListaReproduccion lista) {
        if (listaDao.existsById(id)) {
            ListaReproduccion existingPlaylist = listaDao.findById(id).orElseThrow();
            existingPlaylist.setDescripcion(lista.getDescripcion());
            existingPlaylist.setNombre(lista.getNombre());
            listaDao.save(existingPlaylist);
        } else {
            throw new RuntimeException("La playlist no existe");
        }
    }

    public void eliminarPlaylist(Long id) {
        if (listaDao.existsById(id)) {
            listaDao.deleteById(id);
        } else {
            throw new RuntimeException("La playlist no existe");
        }
    }
}
