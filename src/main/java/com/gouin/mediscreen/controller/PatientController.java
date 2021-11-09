package com.gouin.mediscreen.controller;

import com.gouin.mediscreen.model.Patient;
import com.gouin.mediscreen.service.impl.PatientServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin("*") a approfondi
@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientServiceImpl patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        log.info("Controller getAllPatients");
        List<Patient> patients = this.patientService.findAllPatient();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Patient> getPatientById(@RequestParam int id){
        Patient patient = this.patientService.findPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping(path ="/{firstName}/{lastName}")
    @ResponseBody
    public ResponseEntity<Patient> getPatientByFirtNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        Patient patient = this.patientService.findPatientByFirstNameAndLastName(firstName, lastName);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient newPatient = this.patientService.addPatient(patient);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
       Patient updatePatient = this.patientService.updatePatient(patient);
        return new ResponseEntity<>(updatePatient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable int id){
        this.patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
