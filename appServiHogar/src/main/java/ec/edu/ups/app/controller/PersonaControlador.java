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

import ec.edu.ups.app.data.CategoriaPerDAO;
import ec.edu.ups.app.data.PersonaDAO;
import ec.edu.ups.app.model.Categoria;
import ec.edu.ups.app.model.Persona;

@ManagedBean
@ViewScoped	
public class PersonaControlador {
	
	private Persona persona;
	private List<Persona> personas;
	private List<SelectItem> listpersonas;
	private String id;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private PersonaDAO pdao;
	
	@Inject
	private CategoriaPerDAO catperdao;
	
	
	@PostConstruct
	public void init(){
		persona=new Persona();
		loadPersonas();
	}

	//getters and setters
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		loadDatosEditar(id);
	}

	public PersonaDAO getPdao() {
		return pdao;
	}

	public void setPdao(PersonaDAO pdao) {
		this.pdao = pdao;
	}
	

	public List<SelectItem> getListpersonas() {
		this.listpersonas = new ArrayList<SelectItem>();
		listpersonas.clear();
		for (Persona cat : personas){
			SelectItem catItem = new SelectItem(cat.getCedula(), cat.getNombres());
			this.listpersonas.add(catItem);
		}
		return listpersonas;
	}

	public void setListpersonas(List<SelectItem> listpersonas) {
		this.listpersonas = listpersonas;
	}

	//funciones
	public String editar(){
		try{
			if(this.id!=null){
				System.out.println(persona);
				pdao.guardar(persona);
				loadPersonas();
				return "listarProveedor";
			}else{
				pdao.insertar(persona);
				return "RegistroProveedores";
			}
		}catch(Exception e){
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
            return null; 
		}
		
	}
		public String guardar(int itemCategoria){
			try{ 
				System.out.println("Holaaaaaaaaa");
				System.out.println(persona);
				System.out.println(itemCategoria);
				persona.setCategoria(catperdao.leer(itemCategoria));
				System.out.println(persona);
				if(persona.getCedula().equals("")){
					return "registro-incorrecto";
				}else{
					pdao.guardar(persona);
					loadPersonas();
					return "listarProveedor";
				}
				
			}catch(Exception e){
				String errorMessage = getRootErrorMessage(e);
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	            facesContext.addMessage(null, m);
	            return null; 
			}
			
		}
		
		public String loadDatosBuscar(String cedula){
			System.out.println("holaaaaaa");
			persona = pdao.leer(cedula);
			return "listarProveedor";
		}
		
		public String loadDatosEditar(String cedula){
			persona = pdao.leer(cedula);
			return "editarProveedor";
		}
		
		public String loadDatosEliminar(String cedula){
			pdao.borrar(cedula);
			init();
			return "listarProveedor";
		}
		
		
		private void loadPersonas() {
			personas = pdao.listadoPersonas();
		}
		
		public String eliminar(String cedula){
	    	pdao.borrar(cedula);
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
