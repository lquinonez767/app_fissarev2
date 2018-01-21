package ec.edu.ups.app.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.app.data.UsuarioDAO;
import ec.edu.ups.app.model.Usuario;

@Path("/web")
public class LoginRest {
	@Inject
	private UsuarioDAO dao;	
	
	@GET
	@Path("/login")
	@Produces("application/json")
	public Usuario getCategoria(@QueryParam("username") String username, @QueryParam("password") String password) {
		System.out.println("holaaaaaaaa1");
		List<Usuario> cat = dao.getUsuario(username, password);
		System.out.println(cat);
		return cat.get(0);
	}
}
