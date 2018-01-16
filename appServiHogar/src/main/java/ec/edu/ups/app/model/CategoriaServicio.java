package ec.edu.ups.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tbl_categoriasrv")
@NamedQuery(name="CategoriaServicio.findAll", query="SELECT c FROM CategoriaServicio c")
public class CategoriaServicio {

	@Id
	@NotNull
	@NotBlank
	private String codigo;
	
	@NotBlank
	@Size(min=4, max=60)
	private String descripcion;
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Servicio servicio;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@Override
	public String toString() {
		return "CategoriaServicio [codigo=" + codigo + ", descripcion=" + descripcion + ", servicio=" + servicio + "]";
	}
	
	
}
