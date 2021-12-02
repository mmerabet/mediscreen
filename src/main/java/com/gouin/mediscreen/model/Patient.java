package com.gouin.mediscreen.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;
    @NotBlank(message = "Veuillez mettre un prenom")
    private String firstName;
    @NotBlank(message = "Veuillez mettre un nom")
    @Column(unique = true)
    private String lastName;
    @NotEmpty(message = "Veuillez mettre une date de naissance")
    private String birthdate;
    @NotBlank(message = "Veuillez choisir le genre")
    private String gender;
    private String address;
    private String phone;
}
