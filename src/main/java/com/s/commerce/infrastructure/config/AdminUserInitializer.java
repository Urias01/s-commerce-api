package com.s.commerce.infrastructure.config;

import com.s.commerce.application.user.createAdmin.CreateDefaultAdminUseCase;
import com.s.commerce.config.AppProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final CreateDefaultAdminUseCase createDefaultAdminUseCase;
    private final AppProperties appProperties;

    public AdminUserInitializer(CreateDefaultAdminUseCase createDefaultAdminUseCase, AppProperties appProperties) {
        this.createDefaultAdminUseCase = createDefaultAdminUseCase;
        this.appProperties = appProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        this.createDefaultAdminUseCase.execute(
                appProperties.getAdminName(),
                appProperties.getAdminEmail(),
                appProperties.getAdminPassword());
    }
}
