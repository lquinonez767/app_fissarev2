package ec.edu.ups.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="tbl_usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT p FROM Usuario p")
public class Usuario{
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "id", updatable = false, nullable = false)
	  private int id;
	  
	  @NotNull
	  @Column(length=13)
	  private String sesion;
	  
	  @NotNull
	  @Column(length=100)
	  @Email
	  private String username;
	  
	  @NotNull
	  @Column(length=100)
	  private String password;
	  
	  @NotNull
	  @Column(length=15)
	  private String rolUsuario;
	  
	  @OneToOne//(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.REMOVE},optional=true)
	  @JoinColumn(name="cedula", nullable=false)
	  private Persona persona;

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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", sesion=" + sesion + ", username=" + username + ", password=" + password
				+ ", rolUsuario=" + rolUsuario + ", persona=" + persona + "]";
	}

	
	  
	
	
	
	  
	  
	

}
