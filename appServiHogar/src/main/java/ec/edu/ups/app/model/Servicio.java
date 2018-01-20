package ec.edu.ups.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tbl_servicio")
@NamedQuery(name="Servicio.findAll", query="SELECT s FROM Servicio s")
public class Servicio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull(message="Campo requerido")
	@Column(name="ser_codigo")
	private int codigo;
	
	@NotBlank(message="Campo requerido")
	@Size(min=4, max=50, message="Debe contener entre 4 y 60 caracteres")
	@Column(name="ser_nombre_servicio")
	private String nombreServicio;
	
	@NotBlank(message="Campo requerido")
	@Size(min=4, max=100, message="Debe contener entre 4 y 100 caracteres")
	@Column(name="ser_descripcion")
	private String descripcion;

	@Column(name="ser_valor_servicio")
	private int valorServicio;
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo", nullable=false)
	private CategoriaServicio categoriaservicio;
/*	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigoPedido", nullable=false)
	private Pedido pedido;
	
	*/
	
	
	// getters and setters ------------------------
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(int ValorServicio) {
		this.valorServicio = ValorServicio;
	}

	public CategoriaServicio getCategoriaservicio() {
		return categoriaservicio;
	}

	public void setCategoriaservicio(CategoriaServicio categoriaservicio) {
		this.categoriaservicio = categoriaservicio;
	}

		
	
	@Override
	public String toString() {
		return "Servicio [codigo=" + codigo + ", nombreServicio=" + nombreServicio + ", descripcion=" + descripcion
				+ ", valorServicio=" + valorServicio + ", categoriaservicio=" + categoriaservicio + "]";
	}
		
	
}
