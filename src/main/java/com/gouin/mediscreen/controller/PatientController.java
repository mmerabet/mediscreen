package com.gouin.mediscreen.controller;

import com.gouin.mediscreen.model.Patient;
import com.gouin.mediscreen.service.impl.PatientServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin("*") a approfondi
@Slf4j
@RestController
@RequestMapping("/patient")
@Api(description = "descriptionnnn")
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

    @GetMapping("/id")
    public ResponseEntity<Patient> getPatientById(@RequestParam int id){
        Patient patient = this.patientService.findPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<Patient> getPatientByFirtNameAndLastName(@RequestParam String firstName, @RequestParam String lastName){
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
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Patient with id { id } to update doesn't exist")})
    @ApiOperation(value = "Supprimer un patient selon son ID")
    public ResponseEntity<?> deletePatient(@PathVariable int id){
        this.patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
