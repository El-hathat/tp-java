package com.dbconnect.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Joueur {
    private Long id;
    private String nom;
    private String position;
    private int numero;
    private Equipe equipe;

    public Joueur(String nom, String position, int numero, Equipe equipe) {
        this.nom = nom;
        this.position = position;
        this.numero = numero;
        this.equipe = equipe;
    }
// Constructeurs, getters et setters
}
