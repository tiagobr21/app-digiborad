package authentication.authentication.modules.user;

import authentication.authentication.modules.user.dto.CreateUserRoleDTO;
import authentication.authentication.modules.user.entities.User;
import authentication.authentication.modules.user.repository.UserRepository;
import authentication.authentication.modules.user.services.CreateRoleUserService;
import authentication.authentication.modules.user.services.CreateUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/users")

public class UserController {

  @Autowired
  CreateUserService createUserService;

  @Autowired
  CreateRoleUserService createRoleUserService;

  @Autowired
  UserRepository userRepository;

  @PreAuthorize("hasRole('ADMIN')('USER')") // Autorização para o Administrador e Usuário
  @GetMapping//Listar todos
  public List<User> list() {
    return createUserService.listAll();
  }

  @PreAuthorize("hasRole('ADMIN')('USER')") // Autorização para o Administrador e Usuário
  @GetMapping("/{id}")//Rota para Listar por id
  public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
    Optional<User> userModelOptional = createUserService.findById(id);
    if (!userModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
  }
  @PreAuthorize("hasRole('ADMIN')") //Autorização somente para Administrador
  @PostMapping("/create")// Rota para Criar novo Usuário
  public User create(@RequestBody User user) {
    return createUserService.execute(user);
  }

  @PreAuthorize("hasRole('ADMIN')") //Autorização somente para Administrador
  @PostMapping("/role")// Rota para Definir os papeis dos Usuários
  public User role(@RequestBody CreateUserRoleDTO createUserRoleDTO) {
    return createRoleUserService.execute(createUserRoleDTO);
  }

  @PreAuthorize("hasRole('ADMIN')") //Autorização somente para Administrador
  @DeleteMapping("delete/{id}") // Rota para Deletar Usuários por id
  public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
    Optional<User> userModelOptional = createUserService.findById(id);
    if (!userModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    createUserService.delete(userModelOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
  }

  @PreAuthorize("hasRole('ADMIN')") //Autorização somente para Administrador
  @PutMapping("update/{id}") // Rota para Atualizar Usuário por id
  public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
                                           @RequestBody User user){
    Optional<User> userModelOptional = createUserService.findById(id);
    if (!userModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    userRepository.save(user);
    return ResponseEntity.status(HttpStatus.OK).body("User updated successfully.");
  }


  @PostMapping("/upload") // Rota para Envair arquivos via rest
  public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile file) {
    log.info("Recebendo o arquivo: ", file.getOriginalFilename());
    var path = "C:/Users/Tiago/Documents/GitHub/app-digiboard/src/main/webapp/resources/images/users/";
    var caminho = path  + UUID.randomUUID() + "." + extrairExtensao(file.getOriginalFilename());

    log.info("Novo arquivo"+ caminho);

    try {
      Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
      return new ResponseEntity<>("{\"mensagem\": \"{Arquivo carregado com sucesso!\"}", HttpStatus.OK);
    } catch (Exception e) {
      log.error("Erro ao processar arquivo",e);

      return new ResponseEntity<>("{\"mensagem\": \"{Erro ao carregar o arquivo!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  private String extrairExtensao(String nomeArquivo){
    int i = nomeArquivo.lastIndexOf(".");
    return nomeArquivo.substring(i+1);
  }

}
