package ec.edu.ups.app.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.app.data.CategoriaSrvDAO;
import ec.edu.ups.app.model.CategoriaServicio;

@ManagedBean
@ViewScoped	
public class CategoriaSrvControlador {
	private CategoriaServicio catservicio;
	private List<CategoriaServicio> catservicios;
	private String id;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private CategoriaSrvDAO catsrvdao;
	
	//getters and setters

	public CategoriaServicio getCatservicio() {
		return catservicio;
	}

	public void setCatservicio(CategoriaServicio catservicio) {
		this.catservicio = catservicio;
	}

	public List<CategoriaServicio> getCatservicios() {
		return catservicios;
	}

	public void setCatservicios(List<CategoriaServicio> catservicios) {
		this.catservicios = catservicios;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CategoriaSrvDAO getCatsrvdao() {
		return catsrvdao;
	}

	public void setCatsrvdao(CategoriaSrvDAO catsrvdao) {
		this.catsrvdao = catsrvdao;
	}

	@Override
	public String toString() {
		return "CategoriaSrvControlador [catservicio=" + catservicio + ", catservicios=" + catservicios + ", id=" + id
				+ ", facesContext=" + facesContext + ", catsrvdao=" + catsrvdao + "]";
	}

	
	
	
	
}
