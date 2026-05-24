package com.s.commerce.user;

import com.s.commerce.application.user.create.CreateUserRequest;
import com.s.commerce.application.user.create.CreateUserResponse;
import com.s.commerce.application.user.create.CreateUserUseCase;
import com.s.commerce.domain.common.security.IPasswordHasher;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.enums.UserRole;
import com.s.commerce.domain.user.repository.IUserRepository;
import com.s.commerce.domain.user.valueObject.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserTest {

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IPasswordHasher passwordHasher;

    @Test
    @DisplayName("Should be able to create a customer successfully")
    public void shouldBeAbleToCreateACustomerSuccessfully() {

        CreateUserRequest request = new CreateUserRequest(
                "John Doe",
                new Email("john@doe.com"),
                "123",
                "123");

        when(userRepository.findByEmail(request.email())).thenReturn(Optional.empty());

        when(passwordHasher.hash(request.password())).thenReturn("hashedPassword");

        when(userRepository.create(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateUserResponse response = createUserUseCase.execute(request);

        verify(userRepository).findByEmail(request.email());
        verify(passwordHasher).hash(request.password());
        verify(userRepository).create(argThat(user ->
                user.getPassword().value().equals("hashedPassword") &&
                        user.getRole().equals(UserRole.CUSTOMER)
        ));

        assertNotNull(response.id());
    }
}
