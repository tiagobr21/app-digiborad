package authentication.authentication.modules.user.Beans;


import authentication.authentication.modules.user.entities.User;
import authentication.authentication.modules.user.repository.UserRepository;
import authentication.authentication.modules.user.services.CreateUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "usersBN")
@RequestScoped
@ViewScoped
public class UsersBN {

    @Autowired
    private UserRepository userRepository;

    @Inject
    private CreateUserService createUserService;

    @Getter
    @Setter
    private List<User> users = new ArrayList<>();

    @Getter
    @Setter
    private User user;


    private List<User> userslist;
    public void onload(){
        this.userslist = userRepository.findAll();
    }

   @PostConstruct
    public List<User> list() {
        users = userRepository.findAll();
        return users;
    }

    public String delete(User user){
        createUserService.delete(user);
        return "index?faces-redirect=true";
    }

    public Integer getListSize() {
        return users.size();
    }

    public void setListSize(Integer size) {
        // Método criado para ser utilizado pelo primefaces
    }
}