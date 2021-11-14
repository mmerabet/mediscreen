package com.gouin.mediscreen.repository;

import com.gouin.mediscreen.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findById(int id);

    Optional<Patient> deleteById(int id);

    Optional<Patient> findPatientByFirstNameAndLastName(String firstName, String lastName);
}
