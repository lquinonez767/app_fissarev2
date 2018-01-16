package ec.edu.ups.app.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Servicio {

	@Id
	@NotNull
	@NotBlank
	@Size(min=5, max=5)
	@Column(unique=true, nullable=false)
	private String codigo;
	
	@NotBlank
	@Size(min=4, max=20)
	private String nombre;
	
	@NotBlank
	@Size(min=4, max=20)
	private String descripcion;
	
	
	private double price;
	
	// get an set
	
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Servicio [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", price=" + price
				+ "]";
	}
	
	
	
	
	
	
	
}
