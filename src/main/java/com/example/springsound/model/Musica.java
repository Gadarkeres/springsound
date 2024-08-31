package com.example.springsound.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "musicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Musica {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String titulo;

    @ManyToOne
    private Artista artista;

    public String toString() {
        return "Musica{id=" + id + ", titulo='" + titulo + "', artista=" + (artista != null ? artista.getNome() : "N/A")
                + "}";
    }
}
