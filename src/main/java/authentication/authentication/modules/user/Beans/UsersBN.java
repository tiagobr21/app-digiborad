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


    // função para recarregar a página
    public void onload(){
        this.users = userRepository.findAll();
    }

    // obter o usuário
    public User getUser() {
        return this.user;
    }

    // função preparar um novo usuário para a criação
    @PreAuthorize("hasRole('ADMIN')") // permissão somente para administradores para essa função
    public void prepararNovoUser(){
        this.user = new User();
    }

    // função para criar um novo usuário pelo formulário
    @PreAuthorize("hasRole('ADMIN')") // permissão somente para administradores para essa função
    public void salvar(){
        this.createUserService.execute(user);

    }

    // listar todos os usuários
    @PostConstruct
    public List<User> list() {
        this.users = userRepository.findAll();
        return users;
    }


    @PreAuthorize("hasRole('ADMIN')") // permissão somente para administradores para essa função
    public String delete(User user){
        this.createUserService.delete(user);  //função para deletar um usuário do banco
        return "index?faces-redirect=true";
    }


    @PreAuthorize("hasRole('ADMIN')") // permissão somente para administradores para essa função
    public String edit(User user){
        this.user = user;                      //função para editar um usuário do banco
        return "edit?faces-redirect=true";
    }
    @PreAuthorize("hasRole('ADMIN')") // permissão somente para administradores para essa função
    public String update(){
        this.createUserService.save(this.user);  //função para atualizar um usuário do banco
        return "index?faces-redirect=true";
    }


    public Integer getListSize() {
        return this.users.size();
    }

    public void setListSize(Integer size) {
        // Método criado para ser utilizado pelo primefaces
    }
}