package ec.edu.ups.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tbl_proveedor")
public class Proveedor {
	@Id
	@NotNull
	@NotBlank
	@Size(min=10, max=10)
	private String cedula;
	
	@NotBlank
	@Size(min=4, max=20)
	private String nombres;
	
	@NotBlank
	@Size(min=4, max=20)
	private String direccion;
	
	@Size(min=9, max=9)
	@Pattern(regexp = "[\\s]*[0-9]*[0-9]+",message="Ingresar solo números")
	private String telefono;
	
	@Size(min=10, max=10)
	@Pattern(regexp = "[\\s]*[0-9]*[0-9]+",message="Ingresar solo números")
	private String celular;
	
	@NotBlank
	@Email
	private String email;
	
	@Min(value=1, message="Valor minimo 1")
	private int experiencia;
	
	@NotBlank
	@Size(min=4, max=60)
	private String descripcion;

	
	
	//getters and setters
	
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Proveedor [cedula=" + cedula + ", nombres=" + nombres + ", direccion=" + direccion + ", telefono="
				+ telefono + ", celular=" + celular + ", email=" + email + ", experiencia=" + experiencia
				+ ", descripcion=" + descripcion + "]";
	}
	
	

	

}

