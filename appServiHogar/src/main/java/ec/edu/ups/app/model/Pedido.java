package ec.edu.ups.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="tbl_pedido")
public class Pedido {
	
	@Id
	@Column(name="ped_codigo",length=10)
	@NotNull
	private int codigo;
	
	@Column(name="ped_fecha")
	@Temporal(TemporalType.DATE)
	@NotNull(message="Ingrese la fecha")
	private Date fecha;
	
	@Column(name="ped_estado")
	private String estado;

	//bi-directional many-to-one association to Persona
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cedula")
	private Persona persona;
	
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", fecha=" + fecha + ", estado=" + estado + "]";
	}
	
	
	
	
	
}
