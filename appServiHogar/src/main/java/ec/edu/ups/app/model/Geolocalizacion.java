package ec.edu.ups.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Geolocalizacion {
	@Id
	@Column(name="geo_codigo")
	private int codigo;
	@Column(name="geo_latitud")
	private float latitud;
	@Column(name="geo_longitud")
	private float longitud;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public float getLatitud() {
		return latitud;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	
	

}
