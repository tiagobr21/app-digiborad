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
  private UUID id;
  @Column(nullable = false, unique = true, length = 30)
  @ManagedProperty("#{param.name}")
  private String name;
  @Column(nullable = false, unique = true, length = 20)
  @ManagedProperty("#{param.username}")
  private String username;
  @ManagedProperty("#{param.password}")
  @Column(nullable = false, unique = true)
  private String password;
  @ManagedProperty("#{param.address}")
  @Column(nullable = false, unique = true, length = 50)
  private String address;

  @ManagedProperty("#{param.dateofbirth}")
  @Column(nullable = false, unique = true)
  private LocalDate dateofbirth;


  @Lob
  @Column(name = "imageUser")
  private byte[] imageUser;



  @ManyToMany
  private List<Role> roles;

}
