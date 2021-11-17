package com.gouin.mediscreen.controller;

import com.gouin.mediscreen.model.Patient;
import com.gouin.mediscreen.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientServiceImpl patientService;

    private List<Patient> patients;


    @BeforeEach
    void setUp() {
        this.patients = new ArrayList<>();
        this.patients.add(new Patient(12, "Jacque", "Chirac", "12/03/1775", "Male", "rue de la paix", "000"));
        this.patients.add(new Patient(13, "Tacque", "Chirac", "12/03/1775", "Male", "rue de la paix", "111"));
        this.patients.add(new Patient(14, "Bacque", "Chirac", "12/03/1775", "Male", "rue de la paix", "2222"));
    }

    @DisplayName("GET👩 : /patient/id")
    @Test
    void findPatientByIdTest() throws Exception {
        //Arrange
        when(patientService.findPatientById(any(Integer.class)))
                .thenReturn(new Patient());
        //Act
        MvcResult mvcResult = this.mockMvc.perform(get("/patient/id?id=1"))
                .andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        //Assert
        assertEquals(200, status);

        verify(patientService, times(1)).findPatientById(any(Integer.class));
    }

    @Test
    void findPatientByFirstNameAndLastNameTest() throws Exception {
        //Arrange
        when(patientService.findPatientByFirstNameAndLastName(any(String.class), any(String.class)))
                .thenReturn(new Patient());
        //Act
        MvcResult mvcResult = this.mockMvc.perform(get("/patient?firstName=azaza&lastName=dede"))
                .andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        //Assert
        assertEquals(200, status);
        verify(patientService, times(1)).findPatientByFirstNameAndLastName(any(String.class), any(String.class));
    }

    @Test
    void findAllPatientsTest() throws Exception {
        //Arrange
        when(patientService.findAllPatient()).thenReturn(patients);
        //Act
        this.mockMvc.perform(get("/patient/patients"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        //Assert
        verify(patientService, times(1)).findAllPatient();
    }

    @Test
    void savePatientTest() throws Exception {
        when(patientService.addPatient(any(Patient.class))).thenReturn(any(Patient.class));

        this.mockMvc.perform(post("/patient/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "        \"firstName\": \"Marie\",\n" +
                                "        \"lastName\": \"Durand\",\n" +
                                "        \"birthdate\": \"19/10/1995\",\n" +
                                "        \"gender\": \"female\",\n" +
                                "        \"address\": \"24 rue des andive\",\n" +
                                "        \"phone\": \"0155264572\"\n" +
                                "    }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(patientService, times(1)).addPatient(any(Patient.class));
    }

    @Test
    void deletePatientTest() throws Exception {
        //Arrange

        //Act
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/patient/{id}", anyInt())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Assert
        verify(patientService, times(1)).deletePatient(anyInt());
    }

    @Test
    void updatePatientTest() throws Exception {
        //Arrange
        when(patientService.updatePatient(ArgumentMatchers.any())).thenReturn(new Patient());
        //Act
        this.mockMvc.perform(put("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(" {\n" +
                                " \t\t\"id\": 1,\n" +
                                "        \"firstName\": \"aFrancka\",\n" +
                                "        \"lastName\": \"amorandinia\",\n" +
                                "        \"birthdate\": \"azaza\",\n" +
                                "        \"gender\": \"dada\",\n" +
                                "        \"address\": \"18 rue simon aa\",\n" +
                                "        \"phone\": \"155\",\n" +
                                "        \"userCode\": \"7624d01c-a6da-464e-b38a-d333d98a70e3\"\n" +
                                "    }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Assert
        verify(patientService, times(1)).updatePatient(any(Patient.class));
    }
}
