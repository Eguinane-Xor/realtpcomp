package pack.usr;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    protected TypeUser type;     //enum(locataire, loueur)
    protected String username, password;

    public User(){}

    public User(TypeUser _type, String _username, String _password){
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
