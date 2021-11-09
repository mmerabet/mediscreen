package com.gouin.mediscreen.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


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
    @Column(unique = true)
    private String firstName;
    @NotBlank(message = "Veuillez mettre un nom")
    private String lastName;
//    @NotEmpty(message = "Veuillez mettre une date de naissance")
    private Date birthdate;
    @NotBlank(message = "Veuillez choisir le genre")
    private String gender;
    private String address;
    private String phone;
    @Column(nullable = false, updatable = false)
    private String userCode;
}
