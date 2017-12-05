package ec.edu.ups.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class CategoriaProveedor {
	@Id
	@NotNull
	@NotBlank
	@Size(min=5, max=5)
	private String codigo;
	
	@NotBlank
	@Size(min=4, max=20)
	private String nombre;

	//getters and setters
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "CategoriaProveedor [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	
	
	
	
	
	

}
