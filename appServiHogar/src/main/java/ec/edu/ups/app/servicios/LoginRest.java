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

import ec.edu.ups.app.data.UsuarioDAO;
import ec.edu.ups.app.model.Usuario;

@Path("/web")
public class LoginRest {
	@Inject
	private UsuarioDAO dao;	
	
	@Inject
	private Usuario usuario;
	
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
    public String registro(@FormParam("email") String username, @FormParam("password") String password){
        String result="false";   
        try{
            usuario = new Usuario();
        	System.out.println("holaaaaaaaa1");
    		System.out.println(username);
    		System.out.println(password);
    		System.out.println(usuario);
    		usuario.setRolUsuario("user");
    		System.out.println(usuario);
    		//usuario.setId(3);
    		usuario.setPassword(password);
    		usuario.setSesion("activo");
    		usuario.setUsername(username);
    		System.out.println(usuario);
    		boolean resp = dao.guardar_rest(usuario);
    		System.out.println("holaaaaaaaa3");
    		if (resp==false){
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
}
