package com.suwasethaclinic.JUnitTesting;

import com.suwasethaclinic.controller.PatientController;
import com.suwasethaclinic.controller.PrivilegeController;
import com.suwasethaclinic.dao.PatientDao;
import com.suwasethaclinic.dao.UserDao;
import com.suwasethaclinic.entity.Patient;
import com.suwasethaclinic.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientUnitTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientDao dao;

    @Mock
    private UserDao userDao;

    @Mock
    private PrivilegeController privilegeController;

    private Authentication auth;


    @BeforeEach
    public void setup() {
        auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        when(auth.getName()).thenReturn("testuser");
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
    }





    @Test
    public void testPatientSave_success() {
        // Arrange
        Patient patient = new Patient();
        patient.setTitle("Test Title");
        patient.setFirstname("Ali");
        patient.setLastname("Khan");
        patient.setContactno("0771234567");
        patient.setDateofbirth(LocalDate.parse("1990-01-01"));
        patient.setGender("Male");

        HashMap<String, Boolean> privileges = new HashMap<>();
        privileges.put("insert", true);
        when(privilegeController.getPrivilegeByUserModule("testuser", "Patient")).thenReturn(privileges);


        when(dao.getNextPatNumber()).thenReturn("PR000123");

        User adminUser = new User();
        adminUser.setUsername("admin");
        when(userDao.getUserByUsername("admin")).thenReturn(adminUser);

        // Act
        String result = patientController.patientSave(patient);

        // Assert result string
        assertEquals("OK", result);

         }

}
