package ec.edu.ups.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tbl_categoriasrv")
@NamedQuery(name="CategoriaServicio.findAll", query="SELECT c FROM CategoriaServicio c")
public class CategoriaServicio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name="cas_codigo", unique=true, nullable=false)
	private int codigo;
	
	@NotBlank
	@Size(min=4, max=60)
	@Column(name="cas_nombre")
	private String nombreCategoriaServicio;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombreCategoriaServicio() {
		return nombreCategoriaServicio;
	}

	public void setNombreCategoriaServicio(String nombreCategoriaServicio) {
		this.nombreCategoriaServicio = nombreCategoriaServicio;
	}

	@Override
	public String toString() {
		return "CategoriaServicio [codigo=" + codigo + ", nombreCategoriaServicio=" + nombreCategoriaServicio + "]";
	}

		
	
}