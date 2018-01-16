package ec.edu.ups.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.app.data.ServicioDAO;
import ec.edu.ups.app.model.Servicio;

@ManagedBean
@ViewScoped	
public class ServicioControlador {

	private Servicio servicio;
	private List<Servicio> servicios;
	private String id;
	
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
		loadDatosEditar(id);
	}

	public ServicioDAO getServdao() {
		return servdao;
	}

	public void setServdao(ServicioDAO servdao) {
		this.servdao = servdao;
	}
	
	//funciones
			public String editar(){
				try{
					if(this.id!=null){
						System.out.println(servicio);
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
				public String guardar(){
					try{ 
						if(servicio.getCodigo().equals("")){
							return "listarServicio";
						}else{
							servdao.guardar(servicio);
							loadServicios();
							return "listarServicio";
						}
						
					}catch(Exception e){
						String errorMessage = getRootErrorMessage(e);
			            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			            facesContext.addMessage(null, m);
			            return null; 
					}
					
				}
				
				public String loadDatosBuscar(String codigo){
					System.out.println("holaaaaaa");
					servicio = servdao.leer(codigo);
					return "listarServicio";
				}
				
				public String loadDatosEditar(String codigo){
					servicio= servdao.leer(codigo);
					return "editarServicio";
				}
				
				public String loadDatosEliminar(String codigo){
					servdao.borrar(codigo);
					init();
					return "listado-servicio";
				}
				
				
				private void loadServicios() {
					servicios = servdao.listadoServicios();
				}
				
				public String eliminar(String codigo){
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
	
}
