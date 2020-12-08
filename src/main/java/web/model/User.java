package web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import web.model.dto.UserDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users_security")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "characteristic")
    private String character;

    @Column(name = "iq")
    private int iq;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(int id, String name, String character, int iq, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.character = character;
        this.iq = iq;
        this.password = password;
        this.roles = roles;
    }

    public User(String name, String character, int iq, String password, Set<Role> roles) {
        this.name = name;
        this.character = character;
        this.iq = iq;
        this.password = password;
        this.roles = roles;
    }

    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.password = userDTO.getPassword();
        this.character = userDTO.getCharacter();
        this.iq = userDTO.getIq();
        this.roles = new HashSet<Role>(userDTO.getRoles());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRolesSet() {
        return roles;
    }

    public String getRoles() {
        return roles
                .stream()
                .map(Role::getRole)
                .collect(Collectors.joining(", "));
    }

    public void setRoles(Role role) {
        roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", characteristic='" + character + '\'' +
                ", iq=" + iq +
                '}';
    }
}
