package authentication.authentication.modules.user.services;

import authentication.authentication.modules.user.entities.User;
import authentication.authentication.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

  // Função para criar novos Usuários
  public User execute(User user) {

    User existsUser = userRepository.findByUsername(user.getUsername());


    user.setPassword(passwordEncoder().encode(user.getPassword()));

    User createdUser = userRepository.save(user);

    return createdUser;
  }

 // Função para listar todos os Usuários
  public List<User> listAll() {
    return userRepository.findAll();
  }

  // Função para listar os Usuários por id
  public Optional<User> findById(UUID id) {
    return userRepository.findById(id);
  }

  // Função para deletar os Usuários por id
  @Transactional
  public void delete(User user) {
    userRepository.delete(user);
  }

  // Função para editar os Usuários por id
  @Transactional
  public User save(User user) {
    return userRepository.save(user);
  }




}
