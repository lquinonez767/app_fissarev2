package ec.edu.ups.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_persona")
public class Persona {

	
	@Id
	@Column(name="per_cedula",length=10)
	@Size(min=10, max=10, message="Debe tener 10 digitos")
	@NotEmpty(message="Ingrese la cedula")
	private String cedula;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="per_nombres")
	@NotEmpty(message="Ingrese el nombre")
	private String nombres;
	
	@Email
	@Column(name="per_email")
	@NotEmpty(message="Ingrese el correo")
	private String email;
	
	@Column(name="per_fecha_nac")
	@Temporal(TemporalType.DATE)
	@NotEmpty(message="Ingrese la fecha de nacimiento")
	private Date fechanacimiento;
	
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombres=" + nombres + ", email=" + email + ", fechanacimiento="
				+ fechanacimiento + "]";
	}
	
	
	
}
