package africa.musicmart.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column( nullable = false)
    @JsonIgnore
    private String appUserId;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonIgnore
    private LocalDateTime updatedAt;
    @JsonIgnore
    private Boolean emailVerified;
}