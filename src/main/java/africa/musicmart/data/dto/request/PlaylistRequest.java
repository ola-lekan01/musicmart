package africa.musicmart.data.dto.request;

import africa.musicmart.data.model.Song;
import lombok.Data;

@Data
public class PlaylistRequest {
    private String name;
    private String description;
    private boolean isPublic;
    private Song song;
}
