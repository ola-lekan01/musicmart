package africa.musicmart.controllers;

import africa.musicmart.data.dto.request.PlaylistRequest;
import africa.musicmart.data.dto.response.ApiResponse;
import africa.musicmart.services.PlaylistService;
import africa.musicmart.utils.SpotifyClient;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

    @Autowired
    private SpotifyClient spotifyClient;
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/demo")
    public ResponseEntity<?> createUser(HttpServletRequest request) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data("Hello from Demo EndPoint")
                .timestamp(ZonedDateTime.now())
                .path(request.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get-token")
    public ResponseEntity<ApiResponse> getToken(HttpServletRequest request){
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(spotifyClient.getAccessToken())
                .timestamp(ZonedDateTime.now())
                .path(request.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create-playlist")
    public ResponseEntity<ApiResponse> createPlaylist(@RequestBody  PlaylistRequest playlistRequest, HttpServletRequest httpServletRequest) throws IOException {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(playlistService.createPlaylist(playlistRequest))
                .timestamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}