package com.gouin.mediscreen.service.impl;

import com.gouin.mediscreen.exception.UserNotFoundException;
import com.gouin.mediscreen.model.Patient;
import com.gouin.mediscreen.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAllPatient() {
        return this.patientRepository.findAll();
    }

    public Patient addPatient(Patient patient) {
        return this.patientRepository.save(patient);
    }

    public Patient findPatientById(int idPatient) {
        return this.patientRepository.findById(idPatient)
                .orElseThrow(() -> new UserNotFoundException("User by id " + idPatient + " was not found"));
    }

    public Patient findPatientByFirstNameAndLastName(String firstName, String lastName) {
        return this.patientRepository.findPatientByFirstNameAndLastName(firstName, lastName).
                orElseThrow(() -> new UserNotFoundException("User by id " + firstName + " and " + lastName + " was not found"));
    }

    public Patient deletePatient(int idPatient) {
        return this.patientRepository.deleteById(idPatient)
                .orElseThrow(() -> new UserNotFoundException("User by id " + idPatient + " was not found"));
    }

    public Patient updatePatient(Patient newPatient) {
        Patient patient = findPatientById(newPatient.getId());
        patient.setFirstName(newPatient.getFirstName());
        patient.setLastName(newPatient.getLastName());
        patient.setAddress(newPatient.getAddress());
        patient.setBirthdate(newPatient.getBirthdate());
        patient.setPhone(newPatient.getPhone());
        patient.setGender(newPatient.getGender());
        return this.patientRepository.save(patient);
    }
}
