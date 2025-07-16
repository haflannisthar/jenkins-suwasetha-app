package com.suwasethaclinic.JUnitTesting;

import com.suwasethaclinic.Email.EmailDetails;
import com.suwasethaclinic.Email.EmailService;
import com.suwasethaclinic.controller.PrivilegeController;
import com.suwasethaclinic.controller.UserController;
import com.suwasethaclinic.dao.EmployeeDao;
import com.suwasethaclinic.dao.UserDao;
import com.suwasethaclinic.entity.Employee;
import com.suwasethaclinic.entity.Role;
import com.suwasethaclinic.entity.User;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserSaveUnitTest {

    @InjectMocks
    private UserController userController;


    @Mock
    private UserDao userDao;


    @Mock
    private PrivilegeController privilegeController;


    @Mock
    private EmailService emailService;


    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    private Authentication auth;


    @BeforeEach
    public void setup() {
        auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        when(auth.getName()).thenReturn("testUser");
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);


    }


    @Test
    public void testUserSave() throws MessagingException {


        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setEmail("asz@gail.coma");
        user.setStatus(true);

        Employee employee = new Employee();
        employee.setId(635);
        employee.setCallingname("Mandwa");
        employee.setFullname("Mandwa Fullname");


        user.setEmployee_id(employee);


        // 🔧 Create role and set roles
        Role role = new Role();
        role.setId(1);
        role.setName("ADMIN");

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);


        HashMap<String, Boolean> privileges = new HashMap<>();
        privileges.put("insert", true);
        when(privilegeController.getPrivilegeByUserModule("testUser", "User")).thenReturn(privileges);

        when(userDao.getUserByEmployeeId(user.getEmployee_id().getId())).thenReturn(null);

        when(userDao.getUserByUsername(user.getUsername())).thenReturn(null);

        when(userDao.getUserByEmail(user.getEmail())).thenReturn(null);


        doNothing().when(emailService).sendMail(any(EmailDetails.class));


        // Mock the password encoding
        when(bCryptPasswordEncoder.encode(any(CharSequence.class))).thenReturn("encryptedPass123");


        // Act
        String result = userController.saveUser(user);

        // Assert
        assertEquals("OK", result);


    }
}


// ctrl + alt + L to format code
// ctrl + alt + o to optimize imports
// shift + F6 to rename variable