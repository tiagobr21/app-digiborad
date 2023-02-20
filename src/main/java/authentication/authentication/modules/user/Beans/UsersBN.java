package authentication.authentication.modules.user.Beans;


import authentication.authentication.modules.user.entities.User;
import authentication.authentication.modules.user.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "usersBN")
@RequestScoped
@ViewScoped
public class UsersBN {

    @Autowired
    private UserRepository userRepository;

    @Getter
    @Setter
    private List<User> users = new ArrayList<>();

    @Getter
    @Setter
    private User user;


    @PostConstruct
    public List<User> getListAll() {
        users = userRepository.findAll();
        return users;
    }

    public Integer getListSize() {
        return users.size();
    }

    public void setListSize(Integer size) {
        // Método criado para ser utilizado pelo primefaces
    }
}