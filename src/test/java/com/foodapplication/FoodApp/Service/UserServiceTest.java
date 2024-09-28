package com.foodapplication.FoodApp.Service;

import com.foodapplication.FoodApp.Model.User;
import com.foodapplication.FoodApp.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;  // Class being tested

    @Mock
    private UserRepo userRepo;        // Mocking the repository

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    @Test
    public void testAddUsers() {
        // Arrange
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password1");

        when(userRepo.save(any(User.class))).thenReturn(user);  // Mock the save operation

        // Act
        User savedUser = userService.addUsers(user);

        // Assert
        assertNotNull(savedUser);                               // Verify that the user is saved
        assertEquals("user1", savedUser.getUsername());      // Ensure username is correct
        verify(userRepo, times(1)).save(user);                  // Verify save was called
    }

    @Test
    public void testVerifyUserSuccess() {
        // Arrange
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password1");

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken("user1", "password1");

        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(authToken)).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(jwtService.generateToken("user1")).thenReturn("mocked-jwt-token");

        // Act
        String token = userService.verify(user);

        // Assert
        assertEquals("mocked-jwt-token", token);  // Ensure the token is generated correctly
    }

    @Test
    public void testVerifyUserFailure() {
        // Arrange
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password11");

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken("user1", "password11");

        when(authenticationManager.authenticate(authToken)).thenThrow(new BadCredentialsException(""));

        // Act
        String result = userService.verify(user);

        // Assert
        assertEquals("Failed", result);  // Ensure that login fails with wrong credentials
    }
}
