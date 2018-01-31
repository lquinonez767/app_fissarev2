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
import ec.edu.ups.app.data.UsuarioDAO;
import ec.edu.ups.app.model.Persona;
import ec.edu.ups.app.model.Usuario;
import ec.edu.ups.app.util.SessionUtils;

@ManagedBean
@ViewScoped	
public class PersonaControlador {
	
	@Inject // inyectamos la dependencia
	private SessionUtils session;
	private String username; 
	 
	private Persona persona;
	private List<Persona> personas;
	private List<SelectItem> listpersonas;
	private String id;
	private String tipo;
	
	private Usuario usuario;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private PersonaDAO pdao;
	
	@Inject
	private UsuarioDAO udao;
	
	
	@Inject
	private CategoriaPerDAO catperdao;
	
	
	@PostConstruct
	public void init(){
		persona=new Persona();
		usuario=new Usuario();
		System.out.println("holaaaaaaproveddorinit");
		System.out.println("usuarioLogueado");
		username=session.get("usuarioLogueado");
		System.out.println(username);
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
	
	public UsuarioDAO getUdao() {
		return udao;
	}

	public void setUdao(UsuarioDAO udao) {
		this.udao = udao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
		loadPersonas();
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
		
		public String guardar_front(int itemCategoria){
			try{ 
				System.out.println(itemCategoria);
				persona.setCategoria(catperdao.leer(itemCategoria));
				System.out.println(persona);
				if(persona.getCedula().equals("")){
					return "registro-incorrecto";
				}else{
					persona.setChkCliente(true);
					persona.setExperiencia(1);
					persona.setDescripcion("-----");
					persona.setCertServicios("-----");
					System.out.println("HolaaaaaaaaaPersona");
					System.out.println(persona);
					pdao.guardar(persona);
					loadPersonas();
					usuario.setSesion("0");
					usuario.setPersona(persona);
					System.out.println("HolaaaaaaaaaUsuario");
					System.out.println(usuario);
					udao.guardar(usuario);
					return "RegistroExitoso";
				}
				
			}catch(Exception e){
				String errorMessage = getRootErrorMessage(e);
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	            facesContext.addMessage(null, m);
	            return null; 
			}
			
		}
		
		public String guardar_front_prov(int itemCategoria){
			try{ 
				System.out.println(itemCategoria);
				persona.setCategoria(catperdao.leer(itemCategoria));
				System.out.println(persona);
				if(persona.getCedula().equals("")){
					return "registro-incorrecto";
				}else{
					persona.setChkProveedor(true);
					System.out.println("HolaaaaaaaaaPersona");
					System.out.println(persona);
					pdao.guardar(persona);
					loadPersonas();
					usuario.setSesion("0");
					usuario.setPersona(persona);
					System.out.println("HolaaaaaaaaaUsuario");
					System.out.println(usuario);
					udao.guardar(usuario);
					return "RegistroExitoso";
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
			System.out.println(tipo);
			if (tipo==null){
				System.out.println("holaaaaaanulll");
				System.out.println(username); 
				usuario = udao.getUsuariocli(username).get(0);
				System.out.println(usuario);
				persona = usuario.getPersona();
				System.out.println(persona);
				personas = pdao.listadoPersonascli(persona.getCedula());
				System.out.println(persona);
			}else{
				if (tipo.equals("p")){
					System.out.println("holaaaaaaproveedoraaaa");
					personas = pdao.listadoPersonas(tipo);
				}
				if (tipo.equals("c")){
					System.out.println("holaaaaaacliente");
					personas = pdao.listadoPersonas(tipo);
				}
				System.out.println("holaaaaaaclientefin");
			}
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
