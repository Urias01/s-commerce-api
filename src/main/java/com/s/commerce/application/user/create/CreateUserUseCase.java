package com.s.commerce.application.user.create;

import com.s.commerce.application.user.mappers.UserMapper;
import com.s.commerce.domain.common.exceptions.InvalidArgumentException;
import com.s.commerce.domain.common.security.IPasswordHasher;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.repository.IUserRepository;
import com.s.commerce.domain.user.valueObject.HashedPassword;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final IUserRepository userRepository;
    private final IPasswordHasher passwordHasher;

    public CreateUserUseCase(IUserRepository userRepository, IPasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public CreateUserResponse execute(CreateUserRequest request) {

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new InvalidArgumentException("User already exists");
        }

        if(!request.password().equals(request.confirmPassword())) {
            throw new InvalidArgumentException("Passwords not match");
        }

        HashedPassword password = new HashedPassword(passwordHasher.hash(request.password()));

        User user = UserMapper.toEntity(request, password);

        user = this.userRepository.create(user);

        return new CreateUserResponse(user.getId());
    }
}
