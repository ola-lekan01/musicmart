package africa.musicmart.services;

import africa.musicmart.data.dto.request.RegistrationRequest;
import africa.musicmart.data.dto.response.ApiData;

public interface UserService {
    ApiData register(RegistrationRequest registrationRequest);
}
