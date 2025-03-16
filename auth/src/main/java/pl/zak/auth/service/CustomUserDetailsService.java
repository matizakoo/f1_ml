package pl.zak.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zak.auth.entity.Users;
import pl.zak.auth.entity.enums.ERole;
import pl.zak.auth.repository.UsersRepository;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByEmailAndUserRoleNot(username, ERole.GUEST).get();
        return Users.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .userRole(users.getUserRole())
                .build();
    }
}