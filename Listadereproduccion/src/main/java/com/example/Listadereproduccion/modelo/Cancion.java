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
public class Cancion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String artista;
    private String album;
    private String year;

    public Cancion(Long id, String titulo, String artista, String album, String year, List<ListaReproduccion> listasDeReproduccion) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.year = year;
        this.listasDeReproduccion = listasDeReproduccion;
    }

    @ManyToMany(mappedBy = "canciones")
    private List<ListaReproduccion> listasDeReproduccion;

}
