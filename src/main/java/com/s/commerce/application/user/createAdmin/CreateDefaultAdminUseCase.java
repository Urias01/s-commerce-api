package com.s.commerce.application.user.createAdmin;

import com.s.commerce.domain.common.security.IPasswordHasher;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.repository.IUserRepository;
import com.s.commerce.domain.user.valueObject.Email;
import com.s.commerce.domain.user.valueObject.HashedPassword;
import org.springframework.stereotype.Service;

@Service
public class CreateDefaultAdminUseCase {

    private final IUserRepository userRepository;
    private final IPasswordHasher passwordHasher;

    public CreateDefaultAdminUseCase(IUserRepository userRepository, IPasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public void execute(String name, Email email, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            return;
        }

        User admin = User.createAdmin(name, email, new HashedPassword(passwordHasher.hash(rawPassword)));
        userRepository.create(admin);
    }

}
