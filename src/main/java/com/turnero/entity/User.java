package com.turnero.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.turnero.dto.UserDto;
import com.turnero.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name="users")
@Entity
@ToString
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class User  extends AbstractEntity implements UserDetails , Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	@Column( name = "email", nullable = false, unique = true, length = 45)
	private String email;

	@Column(name = "username", nullable = false, unique = true, length = 45)
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
	private boolean expired = false;

	@Column(name = "bloqueado", nullable = false, length = 20)
	private boolean blocked;

	@Column(name = "rol")
	@Enumerated(EnumType.STRING)
	private Role rol;

	//email
	@Column(name = "emaildestinatario", nullable = true, length = 20)
	private String emailDestinatario;

	@Column(name = "emailpassword", nullable = true, length = 20)
	private String emailPassword;


	@Column(name = "smtpPort", nullable = true, length = 20)
	private String smtpPort;

	@Column(name = "smtphost", nullable = true, length = 20)
	private String smtphost;

	@Column(name = "smtp", nullable = true)
	private boolean smtp;

	@Column(name = "enabledSmtp", nullable = true)
	private boolean enableSmtp;

	@Column(name = "income", nullable = true, length = 20)
	private String folderEntrada;

	@Column(name = "bandeja", nullable = true, length = 20)
    private String folderBandeja;

	@Column(name = "secret", nullable = true, length = 40)
    private String secret;
	


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
		return Arrays.asList((GrantedAuthority) () -> rol.getRol());
	}

	@Override
	public String getUsername() {
		return this.username;
	}




	@Override
	public boolean isAccountNonExpired() {
		return expired;
	}




	@Override
	public boolean isAccountNonLocked() {
		return this.blocked;
	}




	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}




	@Override
	public boolean isEnabled() {
		return enabled;
	}


	public User(String email, String username, String password, String firstName, String lastName, Role rol) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rol = rol;
		this.blocked= false;
		this.enabled= false;
		this.expired = false;

	}
	public User(String email, String username, String password, String firstName, String lastName, Role rol , String folderEntrada, String folderBandeja , String secret) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rol = rol;
		this.blocked= true;
		this.enabled= true;
		this.expired = true;
		this.folderEntrada = folderEntrada;
		this.folderBandeja = folderBandeja;
		this.secret = secret;
	}

	

	public User(String email, String username, String password, String firstName, String lastName, Role rol , String folderEntrada, String folderBandeja , String secret, boolean smtp, boolean enableSmtp, String smtpHost, String smtpPort) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rol = rol;
		this.blocked= true;
		this.enabled= true;
		this.expired = true;
		this.folderEntrada = folderEntrada;
		this.folderBandeja = folderBandeja;
		this.secret = secret;
		
		this.smtp = smtp;
		this.enableSmtp = enableSmtp;
		this.smtphost = smtpHost;
		this.smtpPort = smtpPort;
	}
	
	public Role getRole() {
		return this.rol;
	}

}
