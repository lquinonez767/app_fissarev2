package ec.edu.ups.app.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;



@Entity
@Table(name="tbl_categoria")
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria {
	
	
	@Id
	@NotNull
	@Column(name="cat_codigo")
	private int codigo;
	
	@NotBlank
	@Size(min=4, max=60)
	@Column(name="cat_descripcion")
	private String descripcion;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Categoria [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}

	
	

}
