package com.suwasethaclinic.JUnitTesting;


import com.suwasethaclinic.controller.PrivilegeController;
import com.suwasethaclinic.controller.UserController;
import com.suwasethaclinic.dao.UserDao;
import com.suwasethaclinic.entity.User;
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

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDeleteUnitTest {

    @InjectMocks
    private UserController userController;


    @Mock
    private UserDao userDao;


    @Mock
    private PrivilegeController privilegeController;

    private Authentication auth;

    @BeforeEach
    public void setUp() {
        auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        when(auth.getName()).thenReturn("testUser");
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

    }


    @Test
    public void testDeleteUser(){
        User user = new User();


        HashMap<String, Boolean> privileges = new HashMap<>();
        privileges.put("delete", true);
        when(privilegeController.getPrivilegeByUserModule("testUser", "Employee")).thenReturn(privileges);


        when(userDao.getReferenceById(user.getId())).thenReturn(null);

        String result=userController.deleteUser(user);

        assertEquals("User doesn't exists ",result);

    }
}
