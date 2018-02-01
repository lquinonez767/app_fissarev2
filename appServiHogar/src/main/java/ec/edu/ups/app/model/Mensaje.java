package ec.edu.ups.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tbl_mensaje")
@NamedQuery(name="Mensaje.findAll", query="SELECT c FROM Mensaje c")
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo", updatable = false, nullable = false)
	private int codigo;
	
	@NotBlank
	@Size(min=4, max=60)
	@Column(name="descripcion")
	private String descripcion;
	
	//bi-directional many-to-one association to Pedido
	//@OneToMany(fetch=FetchType.LAZY)
	//@JoinColumn(name="codigo")
	//private Pedido pedido;
		
		

	

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
		return "Mensaje [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
}
