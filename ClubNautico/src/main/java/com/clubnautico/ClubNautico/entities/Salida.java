package com.clubnautico.ClubNautico.entities;

import lombok.*;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Salida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salida_id;

    private LocalDateTime fechaRegreso;
    private LocalDateTime fechaSalida;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barco_id")
    private Barco barco;

}
