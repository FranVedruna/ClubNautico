package com.clubnautico.ClubNautico.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patron_id;

    private String nombre;
    private String apellidos;
    private String licencia;
}

