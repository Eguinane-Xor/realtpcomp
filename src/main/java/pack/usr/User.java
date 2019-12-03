package pack.usr;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "User")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @SequenceGenerator(name="user_generator", sequenceName = "user_seq", allocationSize=50)
    protected int id;

    protected TypeUser type;     //enum(locataire, loueur)
    protected String username, password;

    public User(){}

    public User(String _username, String _password, TypeUser _type){
        this.type = _type;
        this.username = _username;
        this.password = _password;
    }

    @Override
    public String toString(){
        return "id: "+this.id+
                "+ type: "+type.name()+
                " name: "+this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        if(this.type==TypeUser.NONE)
            return false;
        else
            return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(this.type==TypeUser.NONE)
            return false;
        else
            return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if(this.type==TypeUser.NONE)
            return false;
        else
            return true;
    }

    @Override
    public boolean isEnabled() {
        if(this.type==TypeUser.NONE)
            return false;
        else
            return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.getType().toString()));
        return authorities;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeUser getType() {
        return type;
    }

    public void setType(TypeUser type) {
        this.type = type;
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
