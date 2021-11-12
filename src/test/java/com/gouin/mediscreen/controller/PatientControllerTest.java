package com.gouin.mediscreen.controller;

import com.gouin.mediscreen.model.Patient;
import com.gouin.mediscreen.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientServiceImpl patientService;

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
        assertEquals(status, 200);

        verify(patientService, times(1)).findPatientById(any(Integer.class));
    }

    @Test
    void findPatientByFirstNameAndLastNameTest() throws Exception {
        //Arrange

        //Act

        //Assert
    }
    @Test
    void findAllPatientsTest() throws Exception {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void savePatientTest() throws Exception {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void deletePatientTest() throws Exception {
        //Arrange

        //Act

        //Assert
    }

}
