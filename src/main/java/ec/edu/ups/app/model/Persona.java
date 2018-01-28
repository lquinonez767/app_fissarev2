package ec.edu.ups.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="tbl_persona")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona {
	
	@Id
	@NotNull
	@NotBlank
	@Size(min=10, max=10)
	@Column(name="per_cedula")
	private String cedula;
	
	@NotBlank
	@Size(min=4, max=20)
	@Column(name="per_nombres")
	private String nombres;
	
	@NotBlank
	@Size(min=4, max=20)
	@Column(name="per_direccion")
	private String direccion;
	
	@Size(min=9, max=9)
	@Pattern(regexp = "[\\s]*[0-9]*[0-9]+",message="Ingresar solo números")
	@Column(name="per_telefono")
	private String telefono;
	
	@Size(min=10, max=10)
	@Pattern(regexp = "[\\s]*[0-9]*[0-9]+",message="Ingresar solo números")
	@Column(name="per_celular")
	private String celular;
	
	@NotBlank
	@Email
	@Column(name="per_email")
	private String email;
	
	@Min(value=1, message="Valor minimo 1")
	@Column(name="per_experiencia")
	private int experiencia;
	
	@NotBlank
	@Size(min=4, max=60)
	@Column(name="per_descripcion")
	private String descripcion;
	
	@NotBlank
	@Size(min=4, max=20)
	@Column(name="per_certServicios")
	private String certServicios;
	
	@Column(name="per_chkProveedor")
	private boolean chkProveedor;
	
	@Column(name="per_chkCliente")
	private boolean chkCliente;
	
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo", nullable=false)
	private Categoria categoria;
	
	@Column(name="per_CodigoServicio")
	private int codigoServicio;
	
	@ManyToMany
	@JoinTable(name="tbl_per_servicio",joinColumns=
									@JoinColumn(name="per_codigoServicio"),
								inverseJoinColumns=
									@JoinColumn(name="ser_codigo"))
	private List<Pedido> pedidos;
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		
		
	}
	
	public String getCertServicios() {
		return certServicios;
	}

	public void setCertServicios(String certServicios) {
		this.certServicios = certServicios;
	}

	public boolean isChkProveedor() {
		return chkProveedor;
	}

	public void setChkProveedor(boolean chkProveedor) {
		this.chkProveedor = chkProveedor;
	}

	public boolean isChkCliente() {
		return chkCliente;
	}

	public void setChkCliente(boolean chkCliente) {
		this.chkCliente = chkCliente;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	//public List<Pedido> getPedidos() {
	//	return pedidos;
	//}

	//public void setPedidos(List<Pedido> pedidos) {
	//	this.pedidos = pedidos;
	//}

	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombres=" + nombres + ", direccion=" + direccion + ", telefono="
				+ telefono + ", celular=" + celular + ", email=" + email + ", experiencia=" + experiencia
				+ ", descripcion=" + descripcion + "]";
	}

	

}
