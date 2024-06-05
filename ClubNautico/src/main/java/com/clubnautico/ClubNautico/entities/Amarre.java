package com.clubnautico.ClubNautico.entities;

import lombok.*;

import jakarta.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Amarre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amarre_id;

    private Float cuota;
    private String ubicacion;
    private String nombre;

}
