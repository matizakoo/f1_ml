package pl.zak.auth.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zak.auth.config.jwt.JwtUtils;
import pl.zak.auth.controller.advice.ResponseHelper;
import pl.zak.auth.dto.TokenDTO;
import pl.zak.auth.dto.UserCredentialsDTO;
import pl.zak.auth.dto.UsersDTO;
import pl.zak.auth.entity.Users;
import pl.zak.auth.service.UserService;
import pl.zak.auth.utils.ControllerEndpoints;

@RestController
@RequestMapping(ControllerEndpoints.GUEST)
@AllArgsConstructor
public class AuthController {
    private final JwtUtils jwtUtils;
    private final UserService userService;
    @PostMapping(value = ControllerEndpoints.LOGIN)
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserCredentialsDTO userCredentialsDTO) throws Exception {
        userService.isUserValid(userCredentialsDTO);
        Users users = userService.getUserByEmail(userCredentialsDTO.getEmail());
        return ResponseEntity.ok(new TokenDTO(jwtUtils.generateToken(users)));
    }

    @PostMapping(value = ControllerEndpoints.REGISTER)
    public ResponseEntity<?> register(@RequestBody @Valid UsersDTO userDTO) throws Exception {
        userService.registerUser(userDTO);
        return ResponseHelper.response200("Zarejestrowano");
    }
}