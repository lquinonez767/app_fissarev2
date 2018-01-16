package ec.edu.ups.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tbl_servicio")
public class Servicio {

	@Id
	@NotNull
	@NotBlank
	@Size(min=5, max=5)
	@Column(name="ser_codigo")
	private String codigo;
	
	@NotBlank
	@Size(min=4, max=20)
	@Column(name="ser_nombre")
	private String nombre;
	
	@NotBlank
	@Size(min=4, max=20)
	@Column(name="ser_descripcion")
	private String descripcion;

	
	
	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Servicio [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	
}
