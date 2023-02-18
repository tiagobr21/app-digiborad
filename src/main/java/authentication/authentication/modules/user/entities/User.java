package authentication.authentication.modules.user.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private UUID id;
  @Column(nullable = false, unique = true, length = 30)
  private String name;
  @Column(nullable = false, unique = true, length = 20)
  private String username;
  @Column(nullable = false, unique = true)
  private String password;
  @Column(nullable = false, unique = true, length = 50)
  private String address;

  @Column(nullable = false, unique = true)
  private LocalDate dateofbirth;

  @ManyToMany
  private List<Role> roles;

}
