package africa.musicmart.data.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationRequest {
    private String app_user_id;
    private String userId;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
}
