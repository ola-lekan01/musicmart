package africa.musicmart.services;

import africa.musicmart.data.dto.request.PlaylistRequest;
import africa.musicmart.data.dto.response.PlaylistResponse;

public interface PlaylistService {

    PlaylistResponse createPlaylist(PlaylistRequest request);
}
