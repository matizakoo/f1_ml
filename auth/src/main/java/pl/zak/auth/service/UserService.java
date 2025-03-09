package pl.zak.auth.service;

import pl.zak.auth.dto.UserCredentialsDTO;
import pl.zak.auth.dto.UsersDTO;
import pl.zak.auth.entity.Users;

public interface UserService {
    Users getUserByEmail(String email) throws Exception;
    void deleteAccount(Integer id);
    Users getGuestByEmail(String email);
    boolean isUserValid(UserCredentialsDTO userCredentialsDTO) throws Exception;
    boolean isPasswordValid(String usersPassword, String dtoPassword);
    void saveNewUser(UsersDTO usersDTO);
    void updateUserDate(Users users);
    void registerUser(UsersDTO usersDTO) throws Exception;
    void updatePassword(Integer uuid, String password);
    Users getUserById(Integer id);
    void changeDetails(UsersDTO usersDTO);
    void saveUser(Users users);
}