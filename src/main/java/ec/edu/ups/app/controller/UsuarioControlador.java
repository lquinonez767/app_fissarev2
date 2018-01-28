package ec.edu.ups.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.app.data.PersonaDAO;
import ec.edu.ups.app.data.UsuarioDAO;
import ec.edu.ups.app.model.Persona;
import ec.edu.ups.app.model.Usuario;
import ec.edu.ups.app.util.SessionUtils;


@Named
@ManagedBean
@RequestScoped	
public class UsuarioControlador {
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	private String id;
	
	private Persona persona;
	
	 @Inject // inyectamos la dependencia
	 private SessionUtils session;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private UsuarioDAO udao;
	
	@Inject
	private PersonaDAO pdao;
	
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
		loadUsuarios();
	}

	
	//getters and setters
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		loadDatosEditar(Integer.parseInt(id));
	}

	public UsuarioDAO getUdao() {
		return udao;
	}

	public void setUdao(UsuarioDAO udao) {
		this.udao = udao;
	}
	
	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public PersonaDAO getPdao() {
		return pdao;
	}


	public void setPdao(PersonaDAO pdao) {
		this.pdao = pdao;
	}

	//funciones
	
		public String editar(){
			try{
				if(this.id!=null){
					System.out.println(usuario);
					udao.guardar(usuario);
					loadUsuarios();
					return "listarUsuario";
				}else{
					udao.insertar(usuario);
					return "listarUsuario";
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
					System.out.println("Holaaaaaaaaa");
					System.out.println(usuario);
					//if(usuario.getUsername().equals("")){
					//	return "listarUsuario";
					//}else{
					udao.guardar(usuario);
					loadUsuarios();
					return "listarUsuario";
					//}
					
				}catch(Exception e){
					String errorMessage = getRootErrorMessage(e);
		            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
		            facesContext.addMessage(null, m);
		            return null; 
				}
				
			}
			
			public String guardar_front_cliente(){
				try{ 
					System.out.println("Holaaaaaaaaa");
					System.out.println(usuario);
					System.out.println(persona);
					//if(usuario.getUsername().equals("")){
					//	return "listarUsuario";
					//}else{
					usuario.setSesion("0");
					usuario.setPersona(persona);
					udao.guardar(usuario);
					loadUsuarios();
					return "RegistroExitoso";
					//}
					
				}catch(Exception e){
					String errorMessage = getRootErrorMessage(e);
		            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
		            facesContext.addMessage(null, m);
		            return "RegistroFallido"; 
				}
				
			}
			
			
			
			public String loadDatosBuscar(String username, String password){
				System.out.println("holaaaaaa1-1");
				usuarios = udao.getUsuario(username, password);
				System.out.println(usuarios);
				return "listarUsuario";
			}
			
			public String loadDatosEditar(int id){
				usuario = udao.leer(id);
				return "editarUsuario";
			}
			
			public String loadDatosEliminar(int id){
				udao.borrar(id);
				init();
				return "listarUsuario";
			}
			
			
			private void loadUsuarios() {
				usuarios = udao.listadoUsuarios();
			}
			
			public String eliminar(int id){
		    	udao.borrar(id);
		    	init();
		    	return "listarUsuario";
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
			
			
			public String login(){
		        String result="false";   
		        try{
		        	List<Usuario> cat = udao.getUsuario(usuario.getUsername(), usuario.getPassword());
		    		System.out.println("holaaaaaaaaUsuarioOK");
		    		System.out.println(cat);
		    		System.out.println(cat);
		    		if (cat.isEmpty()){
		    			result = "RegistroFallido";
		    		}else{
		    			usuario=cat.get(0);
		    			if (usuario.getPersona().isChkProveedor()){
		    				session.add("usuarioLogueado", usuario.getUsername());
			    			System.out.println("holaaaaaaaaUsuarioOK");
			    			System.out.println(session);
			    			result = "listarPedidoprov";
			    			//return cat.get(0);
		    			}else{
		    				result = "RegistroFallido";
		    			}
		    		}
		        }
		        catch(Exception e){
		            e.printStackTrace();
		        }
		        
		        return result;
		    }
			
			public String loginCliente(){
		        String result="false";   
		        try{
		    		List<Usuario> cat = udao.getUsuario(usuario.getUsername(), usuario.getPassword());
		    		System.out.println("holaaaaaaaaUsuarioCliOK");
		    		System.out.println(cat);
		    		System.out.println("holaaaaaaaatamano");
		    		System.out.println(cat.size());
		    		//System.out.println(usuario.getUsername());
		    		if (cat.isEmpty()){
		    			result = "RegistroFallido";
		    		}else{
		    			usuario=cat.get(0);
		    			if (usuario.getPersona().isChkCliente()){
		    				session.add("usuarioLogueado", usuario.getUsername());
			    			System.out.println("holaaaaaaaaUsuarioOK");
			    			System.out.println(session);
			    			result = "listarPedidocli";
			    			//return cat.get(0);
		    			}else{
		    				result = "RegistroFallido";
		    			}
		    		}
		        }
		        catch(Exception e){
		            e.printStackTrace();
		        }
		        
		        return result;
		    }


}
