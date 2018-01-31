package ec.edu.ups.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import ec.edu.ups.app.data.CategoriaSrvDAO;
import ec.edu.ups.app.data.ServicioDAO;
import ec.edu.ups.app.model.CategoriaServicio;
import ec.edu.ups.app.model.Persona;
import ec.edu.ups.app.model.Servicio;

@ManagedBean
@ViewScoped	
public class ServicioControlador {

	private Servicio servicio;
	private List<Servicio> servicios;
	private List<SelectItem> listaservicios;
	private String id;
	
	//private CategoriaSrvControlador catSrvControlador;
	
	@Inject
	private CategoriaSrvDAO catsrvdao;
	
	private CategoriaServicio catservicio; 
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private ServicioDAO servdao;
	
	@PostConstruct
	public void init(){
		servicio = new Servicio();
		loadServicios();
	}
	
	//getters and setters

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		loadDatosEditar(Integer.parseInt(id));
	}

	public ServicioDAO getServdao() {
		return servdao;
	}

	public void setServdao(ServicioDAO servdao) {
		this.servdao = servdao;
	}
	
	
	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
	
	public List<SelectItem> getListaservicios() {
		this.listaservicios = new ArrayList<SelectItem>();
		listaservicios.clear();
		for (Servicio cat : servicios){
			SelectItem catItem = new SelectItem(cat.getCodigo(), cat.getNombreServicio());
			this.listaservicios.add(catItem);
		}
		return listaservicios;
	}

	public void setListaservicios(List<SelectItem> listaservicios) {
		this.listaservicios = listaservicios;
	}
	
	
	
	//-------- funciones

	public String editar(){
		try{
			if(this.id!=null){
				servdao.guardar(servicio);
				loadServicios();
				return "listarServicio";
			}else{
				servdao.insertar(servicio);
				return "listarServicio";
			}
		}catch(Exception e){
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
            return null; 
		}
		
	}
	
	
	
	public String guardar(int itemCategServ) {
		try{ 
			catservicio = catsrvdao.leer(itemCategServ);
			servicio.setCategoriaservicio(catservicio);
			servdao.guardar(servicio);
			loadServicios();
			return "listarServicio";
			
		}catch(Exception e){
			String errorMessage = getRootErrorMessage(e);
	        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	        facesContext.addMessage(null, m);
	        return null; 
		}
		
	}

	
	public String loadDatosBuscar(int codigo){
		servicio = servdao.leer(codigo);
		return "listarServicio";
	}
	
	public String loadDatosEditar(int codigo){
		servicio= servdao.leer(codigo);
		return "editarServicio";
	}
	
	public String loadDatosEliminar(int codigo){
		servdao.borrar(codigo);
		init();
		return "listarServicio";
	}
	
	
	private void loadServicios() {
		servicios = servdao.listadoServicios();
	}
	
	public String eliminar(int codigo){
    	servdao.borrar(codigo);
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
		return "ServicioControlador [servicio=" + servicio + ", servicios=" + servicios + ", listaservicios="
				+ listaservicios + ", id=" + id + ", facesContext=" + facesContext + ", servdao=" + servdao + "]";
	}
	
}
