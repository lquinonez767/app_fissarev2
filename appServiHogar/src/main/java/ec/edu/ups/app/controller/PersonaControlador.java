package ec.edu.ups.app.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import ec.edu.ups.app.model.Persona;

@ManagedBean
public class PersonaControlador {
	
	private Persona persona;
	
	@PostConstruct
	public void init(){
		persona=new Persona();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public String guardar(){
		
		return null;
	}
	

}
