package com.clubnautico.ClubNautico.entities;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Data
@Table(name = "socios")
public class Socio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socio_id;

    private String nombre;
    private String apellidos;
    private String dni;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barco_id")
    private Barco barco;

}
