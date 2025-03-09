package pl.zak.auth.mapper;

import org.mapstruct.Mapper;
import pl.zak.auth.dto.UsersDTO;
import pl.zak.auth.entity.Users;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDTO mapUsersToUsersDTO(Users users);
    Users mapUsersDTOtoUsers(UsersDTO usersDTO);
    List<Users> mapUserListToUsersDTOList(List<UsersDTO> usersDTOS);
    List<UsersDTO> mapUsersDTOListToUsersList(List<Users> usersList);
}