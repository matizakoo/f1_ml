package pl.zak.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.zak.auth.utils.ValidatorUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialsDTO {
    @NotNull
    @Size(min = 5, max = 40)
    @Email
    private String email;
    @NotNull
    @Size(min = 6, max = 31)
    @Pattern(regexp = ValidatorUtils.NO_WHITE_SPACE, message = "Cannot include whitespaces")
    private String password;
}