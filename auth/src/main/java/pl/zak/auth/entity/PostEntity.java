package pl.zak.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.PackagePrivate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String topic;
    @ManyToMany
    @JoinTable(
            name = "post_users_rel",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> allowedUsers;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users author;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments = new ArrayList<>();
}
