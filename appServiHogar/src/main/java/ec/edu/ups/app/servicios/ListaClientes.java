package ec.edu.ups.app.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.app.data.PersonaDAO;
import ec.edu.ups.app.data.UsuarioDAO;
import ec.edu.ups.app.model.Persona;
import ec.edu.ups.app.model.Usuario;

@Path("/web")
public class ListaClientes {
	
	@Inject
	private UsuarioDAO dao;	
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private PersonaDAO pdao;	
	
	@Inject
	private Persona persona;
	
	@GET
    @Path("/listar")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public List<Persona> lista(@FormParam("tipo") String tipo){
		List<Persona> listado=new ArrayList<>();
        try{
        	System.out.println("HolaaaaaaaaaPersonaListadoRest");
			
        	
        	listado=pdao.listadoPersonas(tipo);
        	
			    	
        }
        catch(Exception e){
        	
            e.printStackTrace();
       }
        
        return listado;
    }
	

}
