package ec.edu.ups.app.controller;

import javax.annotation.PostConstruct;

import ec.edu.ups.app.model.Categoria;
import ec.edu.ups.app.model.Persona;

public class CategoriaControlador {
	
	private Categoria categoria;
	
	@PostConstruct
	public void init(){
		categoria=new Categoria();
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public String guardar(){
		
		return null;
	}
	

}
