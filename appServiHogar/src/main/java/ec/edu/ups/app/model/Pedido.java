package ec.edu.ups.app.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ped_codigo", updatable = false, nullable = false)
	private int codigo;
	
	@Column(name="ped_fecha")
	@Temporal(TemporalType.DATE)
	@NotNull(message="Ingrese la fecha")
	private Date fecha;
	
	@Column(name="ped_hora")
	@Temporal(TemporalType.TIME)
	@NotNull(message="Ingrese la fecha")
	private Date hora;
	
	@Column(name="ped_estado")
	private String estado;

	//bi-directional many-to-one association to Persona
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cedula")
	private Persona persona;
	
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

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
	
	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", fecha=" + fecha + ", hora=" + hora + ", estado=" + estado + ", persona="
				+ persona + "]";
	}

	
	
	
	
	
	
}
