package authentication.authentication.modules.user;

import authentication.authentication.modules.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import authentication.authentication.modules.user.dto.CreateUserRoleDTO;
import authentication.authentication.modules.user.entities.User;
import authentication.authentication.modules.user.services.CreateRoleUserService;
import authentication.authentication.modules.user.services.CreateUserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")

public class UserController {

  @Autowired
  CreateUserService createUserService;

  @Autowired
  CreateRoleUserService createRoleUserService;

  @Autowired
  UserRepository userRepository;

  @GetMapping
  public List<User> list() {
    return createUserService.listAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
    Optional<User> userModelOptional = createUserService.findById(id);
    if (!userModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
  }

  @PostMapping("/create")
  public User create(@RequestBody User user) {
    return createUserService.execute(user);
  }

  @PostMapping("/role")
  public User role(@RequestBody CreateUserRoleDTO createUserRoleDTO) {
    return createRoleUserService.execute(createUserRoleDTO);
  }

  @DeleteMapping("delete/{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
    Optional<User> parkingSpotModelOptional = createUserService.findById(id);
    if (!parkingSpotModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    createUserService.delete(parkingSpotModelOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
  }

  @PutMapping("update/{id}")
  public User updateUser(@RequestBody User user){
    return userRepository.save(user);
  }
}
