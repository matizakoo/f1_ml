package pl.zak.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zak.auth.entity.Users;
import pl.zak.auth.entity.enums.ERole;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
    Optional<Users> findByEmail(String email);

    @Query(value = "select u.* from `running-service`.users as u where u.email = ?1 and u.user_role = 'GUEST'", nativeQuery = true)
    Users findGuestByEmail(String email);
    Optional<Users> findById(Integer id);
    Optional<Users> findByEmailAndUserRoleNot(String email, ERole eRole);
    default Optional<Users> findByEmailWhoIsNotGuest(String email) {
        return findByEmailAndUserRoleNot(email, ERole.GUEST);
    }
}