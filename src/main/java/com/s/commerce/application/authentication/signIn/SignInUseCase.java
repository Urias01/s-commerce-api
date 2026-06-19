package com.s.commerce.application.authentication.signIn;

import com.s.commerce.config.AppProperties;
import com.s.commerce.domain.common.exceptions.InvalidArgumentException;
import com.s.commerce.domain.common.security.IPasswordHasher;
import com.s.commerce.domain.common.security.ITokenService;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class SignInUseCase {

    private final IUserRepository userRepository;

    private final IPasswordHasher passwordHasher;

    private final ITokenService tokenService;
    private final AppProperties appProperties;

    public SignInUseCase(IUserRepository userRepository, IPasswordHasher passwordHasher, ITokenService tokenService, AppProperties appProperties) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenService = tokenService;
        this.appProperties = appProperties;
    }

    public SignInResponse execute(SignInRequest request) {

        User user = this.userRepository.findByEmail(request.email())
                .orElseThrow(() -> new InvalidArgumentException("Invalid e-mail or password"));

        if (!passwordHasher.isMatch(request.password(), user.getPassword())) {
            throw new InvalidArgumentException("Invalid e-mail or password");
        }

        String token = this.tokenService.generateToken(user.getId(), user.getRole());

        return new SignInResponse(token, appProperties.getJwtExpirationMs());
    }
}
