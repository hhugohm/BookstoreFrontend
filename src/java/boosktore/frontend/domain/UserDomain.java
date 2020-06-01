package boosktore.frontend.domain;

/**
 *
 * @author hhugohm
 */
public class UserDomain {
    
    private String username;
    
    private String password;
    
    public UserDomain(){}
    
    
    public UserDomain(String username, String password) {
        this.username = username;
        this.password = password;
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
    
}
