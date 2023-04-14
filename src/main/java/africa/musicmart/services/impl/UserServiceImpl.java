package africa.musicmart.services.impl;

import africa.musicmart.data.dto.request.ConfirmTokenRequest;
import africa.musicmart.data.dto.request.LoginRequest;
import africa.musicmart.data.dto.request.RegistrationRequest;
import africa.musicmart.data.dto.response.ApiData;
import africa.musicmart.data.model.AppUser;
import africa.musicmart.data.model.ConfirmToken;
import africa.musicmart.data.repositories.UserRepository;
import africa.musicmart.exception.GenericException;
import africa.musicmart.services.ConfirmTokenService;
import africa.musicmart.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ConfirmTokenService confirmTokenService;

    @Override
    public ApiData signup(RegistrationRequest registrationRequest) {
        if(findByEmailIgnoreCase(registrationRequest.getEmail())
                .isPresent()) throw new GenericException(String.format(" %s already exist ", registrationRequest.getEmail()));
        if(findByUsername(registrationRequest.getUsername())
                .isPresent())  throw new GenericException(String.format("%s already taken", registrationRequest.getUsername()));

        var user = AppUser.builder()
                .name(registrationRequest.getUsername())
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
    private Optional<AppUser>findByUsername(String username){
        return  userRepository.findByName(username);
    }

    @Override
    public AppUser login(LoginRequest loginRequest){
        var findUser = userRepository.findByEmailIgnoreCase(loginRequest.getEmail())
                .orElseThrow(()-> new GenericException(String.format("%s not found", loginRequest.getEmail())));
   if(!passwordEncoder.matches(loginRequest.getPassword(),findUser.getPassword())){
    throw new GenericException("Invalid login details");
     }
    return findUser;
    }


    public String generateToken (AppUser appUser){

        SecureRandom secureRandom=new SecureRandom();
        String token=String.valueOf(1000 + secureRandom.nextInt(9999));
        ConfirmToken confirmationToken = new ConfirmToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                appUser);

        confirmTokenService.saveConfirmToken(confirmationToken);
        return token;
    }


    public String forgotPasswordEmail(String username,String token){
        return "Here's the link to reset your password"
                + "                                      "
                +"                                        "
                +"<p>Hello \"" + username + "\",</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + token + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
    }


    @Override
    public String confirmToken(ConfirmTokenRequest confirmTokenRequest){
        return null;
    }
}