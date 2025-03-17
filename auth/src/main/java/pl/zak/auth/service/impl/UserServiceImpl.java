package pl.zak.auth.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zak.auth.dto.UserCredentialsDTO;
import pl.zak.auth.dto.UsersDTO;
import pl.zak.auth.entity.Users;
import pl.zak.auth.entity.enums.ERole;
import pl.zak.auth.exception.UserExistsException;
import pl.zak.auth.repository.UsersRepository;
import pl.zak.auth.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Users getUserByEmail(String email) throws Exception {
        System.out.println(usersRepository.findAll());
        return usersRepository.findByEmail(email).orElseThrow(
                () -> new Exception("Nie odnaleziono osoby o takim emailu"));
    }

    @Override
    public void deleteAccount(Integer id) {
        Users users = getUserById(id);
        usersRepository.delete(users);
    }

    @Override
    public Users getGuestByEmail(String email) {
        return usersRepository.findGuestByEmail(email);
    }

    @Override
    public boolean isUserValid(UserCredentialsDTO userCredentialsDTO) throws Exception {
        Users users = getUserByEmail(userCredentialsDTO.getEmail());
        return isPasswordValid(users.getPassword(), userCredentialsDTO.getPassword());
    }

    @Override
    public boolean isPasswordValid(String usersPassword, String dtoPassword) {
        if(bCryptPasswordEncoder.matches(dtoPassword, usersPassword)) {
            return true;
        }
        throw new RuntimeException("Wprowadzono nieprawidłowe haslo");
    }

    @Transactional
    @Override
    public void saveNewUser(UsersDTO usersDTO) {
        Users users = Users.builder()
                .email(usersDTO.getEmail().toLowerCase())
                .username(usersDTO.getUsername().toUpperCase())
                .surname(usersDTO.getSurname().toUpperCase())
                .phone(usersDTO.getPhone())
                .password(bCryptPasswordEncoder.encode(usersDTO.getPassword()))
                .userRole(ERole.USER)
                .enabled(true)
                .build();
        usersRepository.save(users);
    }

    @Transactional
    @Override
    public void updateUserDate(Users users) {
        usersRepository.save(users);
    }

    @Transactional
    @Override
    public void registerUser(UsersDTO usersDTO) {
        Users guestUser = getGuestByEmail(usersDTO.getEmail());
        System.out.println("null? " + guestUser != null);
        if (guestUser != null){
            saveNewUser(usersDTO);
        } else {
            try {
                Users users = getUserByEmail(usersDTO.getEmail());
                if (users != null)
                    throw new UserExistsException("Użytkownik o takim emailu jest już w bazie");
            } catch (UsernameNotFoundException e) {
                saveNewUser(usersDTO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }



    @Override
    public void updatePassword(Integer usersId, String password) {
        Users users = getUserById(usersId);
        users.setPassword(bCryptPasswordEncoder.encode(password));
        updateUserDate(users);
    }

    @Override
    public Users getUserById(Integer id) {
        return usersRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Nie odnaleziono osoby o takim emailu"));
    }

    @Override
    public void changeDetails(UsersDTO usersDTO) {
        Users users = getUserById(usersDTO.getId());
        users.setSurname(usersDTO.getSurname());
        users.setUsername(usersDTO.getUsername());
        users.setEmail(usersDTO.getEmail());
        users.setPhone(usersDTO.getPhone());
        updateUserDate(users);
    }

    @Override
    public void saveUser(Users users) {
        usersRepository.save(users);
    }
}