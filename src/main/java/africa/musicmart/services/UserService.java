package africa.musicmart.services;

import africa.musicmart.data.dto.request.ConfirmTokenRequest;
import africa.musicmart.data.dto.request.ForgotPasswordRequest;
import africa.musicmart.data.dto.request.LoginRequest;
import africa.musicmart.data.dto.request.RegistrationRequest;
import africa.musicmart.data.dto.response.ApiData;
import africa.musicmart.data.model.AppUser;


public interface UserService {
    ApiData signup(RegistrationRequest registrationRequest);
    AppUser login (LoginRequest loginRequest);
    String confirmToken(ConfirmTokenRequest confirmTokenRequest);
}