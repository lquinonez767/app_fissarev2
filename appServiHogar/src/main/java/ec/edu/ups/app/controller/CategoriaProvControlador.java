package ec.edu.ups.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.app.data.CategoriaProvDAO;
import ec.edu.ups.app.model.CategoriaProveedor;

@ManagedBean
@ViewScoped	
public class CategoriaProvControlador {
	private CategoriaProveedor catproveedor;
	private List<CategoriaProveedor> catproveedores;
	private String id;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private CategoriaProvDAO catprovdao;
	
	//getters and setters
	
	@PostConstruct
	public void init(){
		catproveedor = new CategoriaProveedor();
		loadCatProveedores();
	}

	public CategoriaProveedor getCatproveedor() {
		return catproveedor;
	}

	public void setCatproveedor(CategoriaProveedor catproveedor) {
		this.catproveedor = catproveedor;
	}

	public List<CategoriaProveedor> getCatproveedores() {
		return catproveedores;
	}

	public void setCatproveedores(List<CategoriaProveedor> catproveedores) {
		this.catproveedores = catproveedores;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		loadDatosEditar(id);
	}

	public CategoriaProvDAO getCatprovdao() {
		return catprovdao;
	}

	public void setCatprovdao(CategoriaProvDAO catprovdao) {
		this.catprovdao = catprovdao;
	}

	
	//funciones
	
	
		public String editar(){
			try{
				if(this.id!=null){
					System.out.println(catproveedor);
					catprovdao.guardar(catproveedor);
					loadCatProveedores();
					return "listarProveedor";
				}else{
					catprovdao.insertar(catproveedor);
					return "RegistroProveedores";
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
					if(catproveedor.getCodigo().equals("")){
						return "AdminPage";
					}else{
						catprovdao.guardar(catproveedor);
						loadCatProveedores();
						return "AdminPage";
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
				catproveedor = catprovdao.leer(codigo);
				return "listarProveedor";
			}
			
			public String loadDatosEditar(String codigo){
				catproveedor = catprovdao.leer(codigo);
				return "editarProveedor";
			}
			
			public String loadDatosEliminar(String codigo){
				catprovdao.borrar(codigo);
				init();
				return "listado-persona";
			}
			
			
			private void loadCatProveedores() {
				catproveedores = catprovdao.listadoCatProveedores();
			}
			
			public String eliminar(String codigo){
		    	catprovdao.borrar(codigo);
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
