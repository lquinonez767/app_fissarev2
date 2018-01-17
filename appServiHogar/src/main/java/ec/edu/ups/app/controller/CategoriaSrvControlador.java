package ec.edu.ups.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import ec.edu.ups.app.data.CategoriaSrvDAO;
import ec.edu.ups.app.model.CategoriaServicio;

@ManagedBean
@ViewScoped	
public class CategoriaSrvControlador {
	
	private CategoriaServicio catservicio;
	private List<CategoriaServicio> catservicios;
	private List<SelectItem> listacategoriaservicios;
	private String id;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private CategoriaSrvDAO catsrvdao;
	
	@PostConstruct
	public void init() {
		catservicio = new CategoriaServicio();
		loadCategoriaServicios();
	}
	
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

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public CategoriaSrvDAO getCatsrvdao() {
		return catsrvdao;
	}

	public void setCatsrvdao(CategoriaSrvDAO catsrvdao) {
		this.catsrvdao = catsrvdao;
	}

	
	public List<SelectItem> getListacategoriaservicios() {
		return listacategoriaservicios;
	}

	public void setListacategoriaservicios(List<SelectItem> listacategoriaservicios) {
		this.listacategoriaservicios = listacategoriaservicios;
	}

	
	
	//-------- funciones
	
	public String editar(){
		try{
			if(this.id!=null){
				System.out.println(catservicio);
				catsrvdao.actualizar(catservicio);;
				loadCategoriaServicios();
				return "listarCategoriaServicio";
			}else{
				catsrvdao.actualizar(catservicio);;
				return "listarCategoriaServicio";
			}
		}catch(Exception e){
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
            return null;
		}
	}
	
	
	
	public String guardar(){
		try{ 
			System.out.println("Entro en Categoria Servicio Controlador rrrrrrrrrrrrrr");
			System.out.println(catservicio);
			
			catsrvdao.guardar(catservicio);
			loadCategoriaServicios();
			return "listarCategoriaServicio";
			
		}catch(Exception e){
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
            return null; 
		}
		
	}
	
	
	public String loadDatosBuscar(int codigo){
		System.out.println("metodo loadDatosBuscar rrrrrrrrrrrrrr");
		catservicio = catsrvdao.leer(codigo);
		return "listarCategoriaServicio";
	}
	
	public String loadDatosEditar(int codigo){
		System.out.println("metodo loadDatosEditar rrrrrrrrrrrrrr");
		catservicio = catsrvdao.leer(codigo);
		return "editarCategoriaServ";
	}
	
	public String loadDatosEliminar(int codigo){
		System.out.println("metodo loadDatosElimiar rrrrrrrrrrrrrr");
		catsrvdao.borrar(codigo);
		init();
		return "listarCategoriaServicio";
	}
	
	
	private void loadCategoriaServicios() {
		catservicios = catsrvdao.listadoCatServicios();
	}
	
	
	public String eliminar(int codigo){
    	catsrvdao.borrar(codigo);
    	init();
    	return null;
    }
	
	
	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
	}

	
	@Override
	public String toString() {
		return "CategoriaSrvControlador [catservicio=" + catservicio + ", catservicios=" + catservicios + ", id=" + id
				+ ", facesContext=" + facesContext + ", catsrvdao=" + catsrvdao + "]";
	}
	
}
