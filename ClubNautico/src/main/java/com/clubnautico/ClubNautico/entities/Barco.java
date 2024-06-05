package com.clubnautico.ClubNautico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Barco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barco_id;

    private String nombre;
    private String matricula;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "amarre_id")
    private Amarre amarre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patron_id")
    private Patron patron;


}
