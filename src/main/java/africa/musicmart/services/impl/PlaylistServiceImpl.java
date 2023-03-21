package africa.musicmart.services.impl;

import africa.musicmart.data.dto.request.PlaylistRequest;
import africa.musicmart.data.dto.response.PlaylistResponse;
import africa.musicmart.data.model.Playlist;
import africa.musicmart.data.repositories.PlaylistRepository;
import africa.musicmart.services.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;


    @Override
    public PlaylistResponse createPlaylist(PlaylistRequest request) {
        Playlist playlist = new Playlist();
        playlist.setName(request.getName());
        playlist.setDescription(request.getDescription());
//        playlist.getSongs().add(request.getSong());

        playlistRepository.save(playlist);

        return null;
    }
}
