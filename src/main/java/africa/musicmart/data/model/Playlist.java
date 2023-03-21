package africa.musicmart.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "Playlist_songs",
//            joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "songs_id"))
//    private List<Song> songs = new ArrayList<>();
}
