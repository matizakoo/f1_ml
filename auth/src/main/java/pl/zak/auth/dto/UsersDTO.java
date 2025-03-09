package pl.zak.auth.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import pl.zak.auth.utils.ValidatorUtils;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDTO {
    private Integer id;
    @NotNull
    @Email
    @Min(5)
    private String email;
    @Size(min = 3, max = 25)
    private String username;
    @Size(min = 2, max = 25)
    private String surname;
    @Size(min = 8, max = 31)
    @Pattern(regexp = ValidatorUtils.NO_WHITE_SPACE)
    private String password;
    private LocalTime localTime;
    private String phone;
}