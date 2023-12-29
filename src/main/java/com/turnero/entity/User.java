package com.turnero.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.turnero.dto.UserDto;
import com.turnero.enums.Role;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name="users")
public class User  implements UserDetails{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, unique = true, length = 45)
    private String username;
    
    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "habilitado", nullable = false, length = 20)
    private boolean enabled;
    
    @Column(name = "credential_expired", nullable = false, length = 20)
    private boolean expired;
    
    @Column(name = "bloqueado", nullable = false, length = 20)
    private boolean blocked;
    
    @JoinTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<Role> roles = new HashSet<Role>();
    
    
    public static User getUser(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword( passwordEncoder.encode(userDto.getPassword()));
        return user;
    }




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		    List<GrantedAuthority> authorities = new ArrayList<>();
		    for (Role role: getRoles()) {
		    	authorities.addAll(role.getAuthorities());
		    }
		    return authorities;
	}




	@Override
	public String getUsername() {
		return this.username;
	}




	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}




	@Override
	public boolean isAccountNonLocked() {
		return this.blocked;
	}




	@Override
	public boolean isCredentialsNonExpired() {
		return this.expired;
	}




	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public User(String email, String password, String username, Role role ) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
		this.blocked = true;
		this.enabled = true;
		this.expired = true;
		this.roles = Arrays.asList(role);
		this.firstName = "No declarado";
		this.lastName = "No declarado";

	}
	public User(String email, String password, String username, Collection<Role> roles ) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
		this.blocked = true;
		this.enabled = true;
		this.expired = true;
		this.roles = roles;
		this.firstName = "No declarado";
		this.lastName = "No declarado";

	}
}
