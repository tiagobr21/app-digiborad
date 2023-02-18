package authentication.authentication.modules.user.services;

import authentication.authentication.modules.product.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import authentication.authentication.modules.user.repository.UserRepository;
import authentication.authentication.modules.user.entities.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreateUserService {

  @Autowired
  UserRepository userRepository;

  private BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public User execute(User user) {

    User existsUser = userRepository.findByUsername(user.getUsername());

    if (existsUser != null) {
      throw new Error("User already exists!");
    }


    user.setPassword(passwordEncoder().encode(user.getPassword()));

    User createdUser = userRepository.save(user);

    return createdUser;
  }

  public List<User> listAll() {
    return userRepository.findAll();
  }

  public Optional<User> findById(UUID id) {
    return userRepository.findById(id);
  }

  @Transactional
  public void delete(User user) {
    userRepository.delete(user);
  }

  @Transactional
  public User save(User user) {
    return userRepository.save(user);
  }


}
