package ec.edu.ups.app.servicios;

import javax.ws.rs.core.MediaType;

import java.sql.DriverManager;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.app.data.CategoriaPerDAO;
import ec.edu.ups.app.data.PersonaDAO;
import ec.edu.ups.app.data.UsuarioDAO;
import ec.edu.ups.app.model.Persona;
import ec.edu.ups.app.model.Usuario;

@Path("/web")
public class LoginRest {
	@Inject
	private UsuarioDAO dao;	
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private PersonaDAO pdao;	
	
	@Inject
	private Persona persona;
	
	@Inject
	private CategoriaPerDAO catperdao;
	
	@POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String login(@FormParam("email") String username, @FormParam("password") String password){
        String result="false";   
        try{
            
        	System.out.println("holaaaaaaaa1");
    		System.out.println(username);
    		System.out.println(password);
    		List<Usuario> cat = dao.getUsuario(username, password);
    		System.out.println("holaaaaaaaa3");
    		System.out.println(cat);
    		if (cat.isEmpty()){
    			result = "false";
    		}else{
    			result = "true";
    			//return cat.get(0);
    		}
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return result;
    }
	
	@POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String registro(@FormParam("cedula") String cedula, 
    		@FormParam("nombres") String nombres,
    		@FormParam("direccion") String direccion,
    		@FormParam("telfijo") String telefono,
    		@FormParam("telmovil") String celular,
    		@FormParam("email") String username, 
    		@FormParam("password") String password){
        String result="false";   
        try{
        	persona = new Persona();
        	persona.setCedula(cedula);
    		persona.setNombres(nombres);
    		persona.setDireccion(direccion);
    		persona.setEmail(username);
    		persona.setTelefono(telefono);
    		persona.setCelular(celular);
    		persona.setCategoria(catperdao.leer(1));
    		persona.setChkCliente(true);
			persona.setExperiencia(1);
			persona.setDescripcion("-----");
			persona.setCertServicios("-----");
			System.out.println("HolaaaaaaaaaPersonaRest");
			System.out.println(persona);
			pdao.guardar(persona);
			
    		usuario = new Usuario();
    		usuario.setSesion("0");
			usuario.setPersona(persona);
    		usuario.setRolUsuario("user");
    		usuario.setPassword(password);
    		usuario.setUsername(username);
    		System.out.println("holaaaaaaaaUsuarioRest");
    		System.out.println(usuario);
    		boolean resp = dao.guardar_rest(usuario);
    		
    		if (resp==false){
    			result = "false";
    		}else{
    			result = "true";
    			//return cat.get(0);
    		}
        }
        catch(Exception e){
        	result = "false";
            e.printStackTrace();
       }
        
        return result;
    }
}
