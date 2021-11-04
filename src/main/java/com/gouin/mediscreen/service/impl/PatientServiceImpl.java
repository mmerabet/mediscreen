package com.gouin.mediscreen.service.impl;

import com.gouin.mediscreen.model.Patient;
import com.gouin.mediscreen.service.PatientService;
import com.gouin.mediscreen.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatient() {
        return this.patientRepository.findAll();
    }

    public Patient getPatientById(int idPatient) {
        return this.patientRepository.findById(idPatient);
    }
    public Patient addPatient(Patient patient){
        return this.patientRepository.save(patient);
    }
}
