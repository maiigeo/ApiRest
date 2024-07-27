package com.example.Listadereproduccion.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Setter
@Getter
@Entity
@Table
public class ListaReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lista;
    private String nombre;
    private String descripcion;

    public ListaReproduccion(Long id_lista, String nombre, String descripcion, List<Cancion> canciones) {
        this.id_lista = id_lista;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.canciones = canciones;
    }

    @ManyToMany
    private List<Cancion> canciones;

}
