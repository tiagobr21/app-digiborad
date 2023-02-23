package authentication.authentication.modules.user.Beans;


import authentication.authentication.modules.user.entities.User;
import authentication.authentication.modules.user.repository.UserRepository;
import authentication.authentication.modules.user.services.CreateUserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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



    public void onload(){
        this.users = userRepository.findAll();
    }

    public User getUser() {
        return this.user;
    }

    public void prepararNovoUser(){
        this.user = new User();
    }


    public void salvar(){
        this.createUserService.execute(user);

    }

    @PostConstruct
    public List<User> list() {
        this.users = userRepository.findAll();
        return users;
    }


    @PreAuthorize("hasRole('ADMIN')")
    public String delete(User user){
        this.createUserService.delete(user);
        return "index?faces-redirect=true";
    }



    public String edit(User user){
        this.user = user;
        return "edit?faces-redirect=true";
    }
    public String update(){
        this.createUserService.save(this.user);
        return "index?faces-redirect=true";
    }


    public Integer getListSize() {
        return this.users.size();
    }

    public void setListSize(Integer size) {
        // Método criado para ser utilizado pelo primefaces
    }
}