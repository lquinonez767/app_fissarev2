package ec.edu.ups.app.servicios;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/pedidos")
public class PedidosRest {
	
	@GET
	@Path("/saludo")
	@Produces("application/json")
	public String saludo(@QueryParam("n") String nombre){
		return "hola " + nombre;
	}
}
