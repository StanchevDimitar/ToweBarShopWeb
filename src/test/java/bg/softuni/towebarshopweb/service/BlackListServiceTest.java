package bg.softuni.towebarshopweb.service;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlackListServiceTest {

    @Mock
    private UserService userService;

    private BlackListService blackListService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        blackListService = new BlackListService(userService);
    }

    @Test
    public void testIsBlacklisted_ReturnsFalse_WhenCurrentlyLoggedInUserIsActive() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        user.setActive(true);

        when(userService.getCurrentlyLoggedInUser()).thenReturn(user);

        // Act
        boolean isBlacklisted = blackListService.isBlacklisted();

        // Assert
        assertFalse(isBlacklisted);
        verify(userService, times(1)).getCurrentlyLoggedInUser();
    }

    @Test
    public void testIsBlacklisted_ReturnsTrue_WhenCurrentlyLoggedInUserIsNotActive() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        user.setActive(false);

        when(userService.getCurrentlyLoggedInUser()).thenReturn(user);

        // Act
        boolean isBlacklisted = blackListService.isBlacklisted();

        // Assert
        assertTrue(isBlacklisted);
        verify(userService, times(1)).getCurrentlyLoggedInUser();
    }

    @Test
    public void testIsBlacklisted_ReturnsFalse_WhenCurrentlyLoggedInUserIsNull() {
        // Arrange
        when(userService.getCurrentlyLoggedInUser()).thenReturn(null);

        // Act
        boolean isBlacklisted = blackListService.isBlacklisted();

        // Assert
        assertFalse(isBlacklisted);
        verify(userService, times(1)).getCurrentlyLoggedInUser();
    }
}
