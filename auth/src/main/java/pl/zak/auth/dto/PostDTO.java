package pl.zak.auth.dto;

import jakarta.persistence.*;
import lombok.*;
import pl.zak.auth.entity.Users;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostDTO {
    private Integer id;
    private String topic;
    private List<UsersDTO> allowedUsers;
    private UsersDTO author;
}
