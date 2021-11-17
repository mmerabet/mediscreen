package com.gouin.mediscreen.service.impl;

import com.gouin.mediscreen.MediscreenApplication;
import com.gouin.mediscreen.exception.UserNotFoundException;
import com.gouin.mediscreen.model.Patient;
import com.gouin.mediscreen.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@WebMvcTest(PatientServiceImplTest.class)
class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    PatientRepository patientRepository;

    @MockBean
    private List<Patient> patients;

    private Patient patient;

    @BeforeEach
    void setUp() {
        this.patients = new ArrayList<>();
        this.patients.add(new Patient(12, "Jacque", "Chirac", "12/03/1775", "Male", "rue de la paix", "000"));
        this.patients.add(new Patient(13, "Tacque", "Chirac", "12/03/1775", "Male", "rue de la paix", "111"));
        this.patients.add(new Patient(14, "Bacque", "Chirac", "12/03/1775", "Male", "rue de la paix", "2222"));

        this.patient = new Patient(15, "marie", "tolbiac", "12/03/1992", "female", "rue simon", "014585763");

    }

    @Test
    void findAllPatient() throws Exception {

        when(patientRepository.findAll()).thenReturn(this.patients);

        List<Patient> newList = patientService.findAllPatient();
        assertEquals(3, newList.size());

        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void addPatient() throws Exception {
        Patient patient = new Patient(14, "Bacque", "Chirac", "12/03/1775", "Male", "rue de la paix", "2222");
        patientService.addPatient(patient);
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void findPatientById() throws Exception {
        when(patientRepository.findById(15)).thenReturn(java.util.Optional.ofNullable(this.patient));

        Patient newPatient = patientService.findPatientById(this.patient.getId());
        assertEquals("marie", newPatient.getFirstName());
        assertEquals("tolbiac", newPatient.getLastName());
        assertEquals("female", newPatient.getGender());
    }

    @Test
    void findPatientByIdThrowException() throws Exception {
        when(patientRepository.findById(anyInt())).thenThrow(new UserNotFoundException("User not found"));
        try {
            patientService.findPatientById(this.patient.getId());
        } catch (Exception e) {

            assertTrue(e instanceof UserNotFoundException);
            assertEquals(e.getMessage(), "User not found");
        }
    }

    @Test
    void findPatientByFirstNameAndLastName() throws Exception {
        when(patientRepository.findPatientByFirstNameAndLastName("marie", "tolbiac")).thenReturn(java.util.Optional.ofNullable(this.patient));

        Patient newPatient = patientService.findPatientByFirstNameAndLastName("marie", "tolbiac");
        assertEquals("marie", newPatient.getFirstName());
        assertEquals("tolbiac", newPatient.getLastName());
        assertEquals("female", newPatient.getGender());
    }

    @Test
    void deletePatient() throws Exception {
        patientService.deletePatient(15);
        verify(patientRepository, times(1)).deleteById(15);
    }

    @Test
    void deletePatientThrowException() throws Exception {
        try {
            patientService.deletePatient(56);
        } catch (Exception e) {

            assertTrue(e instanceof UserNotFoundException);
            assertEquals(e.getMessage(), "User by id " + 56 + " was not found");
        }
    }

    @Test
    void updatePatient() throws Exception {
        when(patientRepository.findById(this.patient.getId())).thenReturn(java.util.Optional.ofNullable(this.patient));
        System.out.println(this.patient);
        patientService.updatePatient(this.patient);
        verify(patientRepository, times(1)).save(this.patient);

    }
    @Test
    public void applicationContextTest() {
        MediscreenApplication.main(new String[] {});
    }
}
