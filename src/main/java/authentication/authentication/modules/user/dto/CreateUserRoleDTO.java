package authentication.authentication.modules.user.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateUserRoleDTO {


  private UUID idUser; // identificação do Usuário

  private List<UUID> idsRoles; // identificação dos papeis

}
