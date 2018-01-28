package ec.edu.ups.app.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.app.model.Pedido;
import ec.edu.ups.app.model.Persona;

@Stateless
public class PedidoDAO {
	
	@Inject
	private EntityManager em;
	
	
	public void insertar(Pedido pedido) {
		em.persist(pedido);
	}
	
	public void actualizar(Pedido pedido) {
		em.merge(pedido);
	}

	public void borrar(int codigo) {
		em.remove(leer(codigo));
	}

	public Pedido leer(int codigo) {
		Pedido p = em.find(Pedido.class, codigo);
		return p;
	}
		
	
	public List<Pedido> listadoPedidos(){
		String jpql = "SELECT p FROM Pedido p";
		Query query = em.createQuery(jpql, Pedido.class);
		List<Pedido> listado = query.getResultList();
		return listado;
	} 
	
	public void guardar(Pedido pedido){
		Pedido p = leer(pedido.getCodigo());
		if (p==null)
			insertar(pedido);
		else
			actualizar(pedido);
	}
	
}
