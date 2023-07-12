package com.pragma.user_microservice.infrastructure.configuration;

import com.pragma.user_microservice.domain.api.IUserServicePort;
import com.pragma.user_microservice.domain.spi.IRolePersistencePort;
import com.pragma.user_microservice.domain.spi.IUserPersistencePort;
import com.pragma.user_microservice.domain.usecase.UserUseCase;
import com.pragma.user_microservice.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.pragma.user_microservice.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.user_microservice.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.user_microservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.user_microservice.infrastructure.out.jpa.repository.IRoleRepository;
import com.pragma.user_microservice.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository iUserRepository;
    private final IUserEntityMapper iUserEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final IRoleEntityMapper iRoleEntityMapper;

    private final IRoleRepository iRoleRepository;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(iUserRepository, iUserEntityMapper, passwordEncoder, rolePersistencePort());
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(iRoleRepository, iRoleEntityMapper);
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}