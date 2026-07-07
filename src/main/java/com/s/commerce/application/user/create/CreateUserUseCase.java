package com.s.commerce.application.user.create;

import com.s.commerce.application.user.mappers.UserMapper;
import com.s.commerce.domain.common.exceptions.InvalidArgumentException;
import com.s.commerce.domain.common.security.IPasswordHasher;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.repository.IUserRepository;
import com.s.commerce.domain.user.valueObject.HashedPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateUserUseCase {

    private final IUserRepository userRepository;
    private final IPasswordHasher passwordHasher;

    public CreateUserUseCase(IUserRepository userRepository, IPasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public CreateUserResponse execute(CreateUserRequest request) {
        log.info("Starting processing to create an user");
        if (userRepository.findByEmail(request.email()).isPresent()) {
            log.warn("User already exists");
            throw new InvalidArgumentException("User already exists");
        }

        if(!request.password().equals(request.confirmPassword())) {
            log.warn("Passwords not match");
            throw new InvalidArgumentException("Passwords not match");
        }

        HashedPassword password = new HashedPassword(passwordHasher.hash(request.password()));

        User user = UserMapper.toEntity(request, password);

        user = this.userRepository.create(user);

        log.info("Create an user successful. userId={}", user.getId());
        return new CreateUserResponse(user.getId());
    }
}
