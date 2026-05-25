package com.s.commerce.auth;

import com.s.commerce.application.authentication.signIn.SignInRequest;
import com.s.commerce.application.authentication.signIn.SignInResponse;
import com.s.commerce.application.authentication.signIn.SignInUseCase;
import com.s.commerce.config.AppProperties;
import com.s.commerce.domain.common.security.IPasswordHasher;
import com.s.commerce.domain.common.security.ITokenService;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.enums.UserRole;
import com.s.commerce.domain.user.repository.IUserRepository;
import com.s.commerce.domain.user.valueObject.Email;
import com.s.commerce.domain.user.valueObject.HashedPassword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SignInTest {

    @InjectMocks
    private SignInUseCase signInUseCase;

    @Mock
    private IUserRepository userRepository;
    @Mock
    private IPasswordHasher passwordHasher;
    @Mock
    private ITokenService tokenService;
    @Mock
    private AppProperties appProperties;

    @Test
    @DisplayName("Should be able to sign in successfully")
    public void shouldBeAbleToSignInSuccessfully() {
        User user = new User("john doe", new Email("john@Doe.com"), new HashedPassword("hashed"), UserRole.CUSTOMER);

        SignInRequest request = new SignInRequest(user.getEmail(), "123456");

        when(userRepository.findByEmail(request.email())).thenReturn(Optional.of(user));

        when(passwordHasher.isMatch(request.password(), user.getPassword())).thenReturn(true);

        when(tokenService.generateToken(user.getId(), user.getRole())).thenReturn("jwtToken");

        when(appProperties.getJwtExpirationMs()).thenReturn(3600L);

        SignInResponse response = this.signInUseCase.execute(request);

        verify(userRepository).findByEmail(request.email());
        verify(passwordHasher).isMatch(request.password(), user.getPassword());
        verify(tokenService).generateToken(user.getId(), user.getRole());

        assertEquals("jwtToken", response.token());
        assertEquals(3600L, response.expiration());
    }
}
