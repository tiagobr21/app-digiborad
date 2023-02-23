package authentication.authentication.modules.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private UUID id;  // Coluna para identificação do usuário
  @Column(nullable = false, unique = true, length = 30)
  @ManagedProperty("#{param.name}")
  private String name; // Coluna para o nome
  @Column(nullable = false, unique = true, length = 20)
  @ManagedProperty("#{param.username}")
  private String username; // Coluna para o nome do usuário
  @ManagedProperty("#{param.password}")
  @Column(nullable = false, unique = true)
  private String password; // Coluna para a senha
  @ManagedProperty("#{param.address}")
  @Column(nullable = false, unique = true, length = 50)
  private String address; // Coluna para o endereço

  @ManagedProperty("#{param.dateofbirth}")
  @Column(nullable = false, unique = true)
  private LocalDate dateofbirth; // Coluna para a data de nascimento


  @Lob
  @Column(name = "imageUser")
  private byte[] imageUser; // Coluna para a imagem



  @ManyToMany
  private List<Role> roles; //listar os papeis

}
