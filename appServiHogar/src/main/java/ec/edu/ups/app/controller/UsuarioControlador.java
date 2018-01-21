package ec.edu.ups.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.app.data.UsuarioDAO;
import ec.edu.ups.app.model.Usuario;



@ManagedBean
@ViewScoped	
public class UsuarioControlador {
	@Inject
	private Usuario usuario;
	private List<Usuario> usuarios;
	private String id;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private UsuarioDAO udao;
	
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
	}

	public UsuarioDAO getUdao() {
		return udao;
	}

	public void setUdao(UsuarioDAO udao) {
		this.udao = udao;
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
					System.out.println("Holaaaaaaaaa");
					System.out.println(usuario);
					if(usuario.getUsername().equals("")){
						return "listarUsuario";
					}else{
						udao.guardar(usuario);
						loadUsuarios();
						return "listarUsuario";
					}
					
				}catch(Exception e){
					String errorMessage = getRootErrorMessage(e);
		            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
		            facesContext.addMessage(null, m);
		            return null; 
				}
				
			}
			
			public String loadDatosBuscar(String username, String password){
				System.out.println("holaaaaaa1-1");
				usuarios = udao.getUsuario(username, password);
				System.out.println(usuarios);
				return "index.xhtml";
			}
			
			public String loadDatosEditar(String username){
				usuario = udao.leer(username);
				return "editarUsuario";
			}
			
			public String loadDatosEliminar(String username){
				udao.borrar(username);
				init();
				return "listarUsuario";
			}
			
			
			private void loadUsuarios() {
				usuarios = udao.listadoUsuarios();
			}
			
			public String eliminar(String username){
		    	udao.borrar(username);
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
