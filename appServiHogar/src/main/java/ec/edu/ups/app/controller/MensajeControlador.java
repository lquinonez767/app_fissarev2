package ec.edu.ups.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;


import ec.edu.ups.app.data.MensajeDAO;
import ec.edu.ups.app.model.Mensaje;

@ManagedBean
@ViewScoped	
public class MensajeControlador {
	private Mensaje mensaje;
	private List<Mensaje> mensajes;
	private List<SelectItem> listamensajes;
	private String id;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private MensajeDAO msgdao;
	
	@PostConstruct
	public void init() {
		mensaje = new Mensaje();
		loadMensajes();
	}

	
	//getters and setters
	
	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public List<SelectItem> getListamensajes() {
		return listamensajes;
	}

	public void setListamensajes(List<SelectItem> listamensajes) {
		this.listamensajes = listamensajes;
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

	public MensajeDAO getMsgdao() {
		return msgdao;
	}

	public void setMsgdao(MensajeDAO msgdao) {
		this.msgdao = msgdao;
	}
	
	///
	
	//-------- funciones
	
		public String editar(){
			System.out.println("Metodo editar() de Mensajes rrrrrrrrrrrrrr");
			try{
				if(this.id!=null){
					System.out.println(mensaje);
					msgdao.actualizar(mensaje);;
					loadMensajes();
					return "listarCategoriaServicio";
				}else{
					msgdao.actualizar(mensaje);
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
				msgdao.guardar(mensaje);
				loadMensajes();
				return "listarCategoriaServicio";			
			}catch(Exception e){
				String errorMessage = getRootErrorMessage(e);
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	            facesContext.addMessage(null, m);
	            return null; 
			}
			
		}
		
		
		public String loadDatosBuscar(int codigo){
			mensaje = msgdao.leer(codigo);
			return "listarCategoriaServicio";
		}
		
		public String loadDatosEditar(int codigo){
			mensaje = msgdao.leer(codigo);
			return "editarCategoriaServ";
		}
		
		public String loadDatosEliminar(int codigo){
			msgdao.borrar(codigo);
			init();
			return "listarCategoriaServicio";
		}
		
		private void loadMensajes() {
			mensajes = msgdao.listadoMensajes();
		}
		
		public String eliminar(int codigo){
	    	msgdao.borrar(codigo);
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
