package com.example.Listadereproduccion.dao;

import com.example.Listadereproduccion.modelo.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionesDao extends JpaRepository<Cancion, Long> {
}
