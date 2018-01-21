package ec.edu.ups.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbl_usuario")
public class Usuario {
	@Id
	  @GeneratedValue
	  private int id;
	  
	  @NotNull
	  @Column(length=13)
	  private String sesion;
	  
	  @NotNull
	  @Column(length=100)
	  private String username;
	  
	  @NotNull
	  @Column(length=100)
	  private String password;
	  
	  @NotNull
	  @Column(length=15)
	  private String rolUsuario;

	//getters and setters
	  
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
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

	public String getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", sesion=" + sesion + ", username=" + username + ", password=" + password
				+ ", rolUsuario=" + rolUsuario + "]";
	}
	  
	
	
	
	  
	  
	

}
