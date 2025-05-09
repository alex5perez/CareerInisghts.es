package com.etrivium.backend.service;

import com.etrivium.backend.controller.request.SigninKeyRequest;
import com.etrivium.backend.controller.response.JwtAuthenticationResponse;
import com.etrivium.backend.controller.request.SignUpRequest;
import com.etrivium.backend.controller.request.SigninRequest;
import com.etrivium.backend.repository.AccessKeyRepository;
import com.etrivium.backend.repository.CompanyRepository;
import com.etrivium.backend.repository.ConfigurationRepository;
import com.etrivium.backend.repository.entity.AccessKeyEntity;
import com.etrivium.backend.repository.entity.CompanyEntity;
import com.etrivium.backend.repository.entity.ConfigurationEntity;
import com.etrivium.backend.repository.entity.UserEntity;
import com.etrivium.backend.repository.UserRepository;
import com.etrivium.backend.service.domain.Role;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final ConfigurationRepository configurationRepository;
    private final AccessKeyRepository accessKeyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        try {
            String rawPassword = request.getPassword();
            String encodedPassword = passwordEncoder.encode(rawPassword);

            logger.debug("Raw password length: {}", rawPassword.length());
            logger.debug("Encoded password: {}", encodedPassword);

            if (!encodedPassword.startsWith("$2a$")) {
                logger.error("Password not encoded properly");
                throw new IllegalStateException("Password not encoded properly");
            }

            var company = CompanyEntity.builder().name(request.getCompany()).build();
            companyRepository.save(company);

            var configuration = ConfigurationEntity.builder()
                    .totalCoursesAllowed(30)
                    .totalDownloadsAllowed(5)
                    .chatQueriesPerMonth(50)
                    .company(company)
                    .build();
            configurationRepository.save(configuration);

            var user = UserEntity.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(encodedPassword)
                    .role(Role.USER)
                    .company(company)
                    .signUpDate(LocalDateTime.now())
                    .build();
            userRepository.save(user);

            // Verify the saved user
            UserEntity savedUser = userRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found after saving"));
            logger.debug("Saved user password length: {}", savedUser.getPassword().length());

            var jwt = jwtService.generateToken(user);
            logger.info("User signed up successfully: {}", user.getEmail());
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } catch (Exception e) {
            logger.error("Error during signup for email: {}", request.getEmail(), e);
            throw new RuntimeException("Error during signup: " + e.getMessage());
        }
    }

    public JwtAuthenticationResponse signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
            var jwt = jwtService.generateToken(user);
            user.setLastSignInDate(LocalDateTime.now());
            userRepository.save(user);
            logger.info("User signed in successfully: {}", user.getEmail());
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } catch (Exception e) {
            logger.error("Error during signin for email: {}", request.getEmail(), e);
            throw new RuntimeException("Error during signin: " + e.getMessage());
        }
    }

    public JwtAuthenticationResponse signinKey(SigninKeyRequest request) {
        try {
            AccessKeyEntity accessKeyEntity = accessKeyRepository.findByKey(request.getKey());
            List<UserEntity> users = userRepository.findByCompanyId(accessKeyEntity.getCompany().getId());
            var jwt = jwtService.generateToken(users.getFirst());
            logger.info("User signed in with key successfully for company: {}", accessKeyEntity.getCompany().getName());
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } catch (Exception e) {
            logger.error("Error during key signin for key: {}", request.getKey(), e);
            throw new RuntimeException("Error during key signin: " + e.getMessage());
        }
    }
}