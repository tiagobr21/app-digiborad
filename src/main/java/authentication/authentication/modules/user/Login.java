package authentication.authentication.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "loginController")
@ManagedBean
@RequestScoped
public class Login {

    private String username;
    private String password;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Função para receber o username e password e autenticar o Usuário
    public String login() {
        try {
            SecurityContextHolder.getContext().getAuthentication();
            Authentication request = new UsernamePasswordAuthenticationToken(this.username, this.password);
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return "users.jr?faces-redirect=true";
        } catch (AuthenticationException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication failed.", "Invalid username or password."));
            return null;
        }
    }

    // Sair do sistema
    public String logout() {
        SecurityContextHolder.clearContext();
        return "/login.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
