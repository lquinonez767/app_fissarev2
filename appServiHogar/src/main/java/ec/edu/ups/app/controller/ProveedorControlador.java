package ec.edu.ups.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.app.data.ProveedorDAO;
import ec.edu.ups.app.model.Proveedor;

@ManagedBean
public class ProveedorControlador {
	private Proveedor proveedor;
	private List<Proveedor> proveedores;
	private String id;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private ProveedorDAO provdao;
	
	@PostConstruct
	public void init(){
		proveedor = new Proveedor();
		loadProveedores();
	}

	//getters and setters
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProveedorDAO getProvdao() {
		return provdao;
	}

	public void setProvdao(ProveedorDAO provdao) {
		this.provdao = provdao;
	}

	//funciones

		public String guardar(){
			try{ 
				System.out.println("Holaaaaaaaaa");
				System.out.println(proveedor);
				if(proveedor.getCedula().equals("")){
					return "registro-incorrecto";
				}else{
					provdao.guardar(proveedor);
					init();
					loadProveedores();
					return "registro-correcto";
				}
				
			}catch(Exception e){
				String errorMessage = getRootErrorMessage(e);
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	            facesContext.addMessage(null, m);
	            return null; 
			}
			
		}
		
		public String loadDatosEditar(String cedula){
			return "datos_defecto";
		}
		
		public String loadDatosEliminar(String cedula){
			provdao.borrar(cedula);
			init();
			return "listado-persona";
		}
		
		
		private void loadProveedores() {
			proveedores = provdao.listadoProveedores();
		}
		
		public String eliminar(String cedula){
	    	provdao.borrar(cedula);
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
