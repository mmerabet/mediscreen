package com.gouin.mediscreen.controller;

import com.gouin.mediscreen.exception.UserNotFoundException;
import com.gouin.mediscreen.model.Patient;
import com.gouin.mediscreen.service.impl.PatientServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/patient")
@OpenAPIDefinition(info = @Info(
        title = "Patient controller",
        version = "1.0",
        description = "Availables methods for patients"
))
@CrossOrigin("*")
public class PatientController {
    private final PatientServiceImpl patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Get all patient", description = "Return the list of all patient",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of all persons", content = @Content(schema = @Schema(implementation = Patient[].class))),
                    @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = UserNotFoundException.class)))
            })
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        log.info("Controller getAllPatients");
        List<Patient> patients = this.patientService.findAllPatient();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Patient> getPatientById(@RequestParam int id) {
        log.info("Controller getPatientById");
        Patient patient = this.patientService.findPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
    @GetMapping()
    @ResponseBody
    public ResponseEntity<Patient> getPatientByFirtNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        log.info("Controller getPatientByFirtNameAndLastName");
        Patient patient = this.patientService.findPatientByFirstNameAndLastName(firstName, lastName);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        log.info("Controller addPatient");
        Patient newPatient = this.patientService.addPatient(patient);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
        log.info("Controller updatePatient");
        Patient updatePatient = this.patientService.updatePatient(patient);
        return new ResponseEntity<>(updatePatient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable int id) {
        log.info("Controller deletePatient");
        this.patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
