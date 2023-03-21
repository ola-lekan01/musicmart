package africa.musicmart.data.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PlaylistResponse {
    private String message;
    private HttpStatus status;
    private Object data;
}
