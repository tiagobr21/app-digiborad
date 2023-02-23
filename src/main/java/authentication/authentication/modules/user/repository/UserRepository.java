package authentication.authentication.modules.user.repository;

import authentication.authentication.modules.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  // obter o Username
  User findByUsername(String username);

  // fazer um consulta no banco para pegar o Usuário e seu papel
  @Query("SELECT u from User u JOIN FETCH u.roles where u.username = :username ")
  User findByUsernameFetchRoles(@Param("username") String username);


}
