package com.example.Listadereproduccion.dao;

import com.example.Listadereproduccion.modelo.ListaReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaReproduccionDao extends JpaRepository<ListaReproduccion, Long> {
    ListaReproduccion findByNombre(String nombre);
}
