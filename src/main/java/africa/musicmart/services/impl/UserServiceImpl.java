package africa.musicmart.services.impl;

import africa.musicmart.data.dto.request.RegistrationRequest;
import africa.musicmart.data.dto.response.ApiData;
import africa.musicmart.data.model.AppUser;
import africa.musicmart.data.repositories.UserRepository;
import africa.musicmart.exception.GenericException;
import africa.musicmart.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public ApiData register(RegistrationRequest registrationRequest) {
        if(findByEmailIgnoreCase(registrationRequest.getEmail())
                .isPresent()) throw new GenericException(String.format("AppUser with %s already exist in the database", registrationRequest.getEmail()));
        var user = AppUser.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail().toLowerCase())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .build();
        userRepository.save(user);

        return ApiData.builder()
                .data("AppUser Registration successful")
                .build();
    }

    private Optional<AppUser> findByEmailIgnoreCase(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }
}